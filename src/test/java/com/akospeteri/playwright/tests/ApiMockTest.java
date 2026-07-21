package com.akospeteri.playwright.tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiMockTest {
    
    @Test
    void shouldMockApiResponse() {
        
        try (Playwright playwright = Playwright.create()) {
            
            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(true));
            
            BrowserContext context = browser.newContext();
            Page page = context.newPage();

            page.route("https://jsonplaceholder.typicode.com/users/1",
                    route -> {
                        System.out.println(">>> REQUEST INTERCEPTED <<<");
                        route.fulfill(
                                new Route.FulfillOptions()
                                        .setStatus(200)
                                        .setContentType("application/json")
                                        .setBody("""
                                                {
                                                  "id":1,
                                                  "name":"Akos Mock User",
                                                  "email":"akos@example.com"
                                                }
                                                """));
                    });
            
            String response = page.evaluate("""
                    async () => {
                        const response = await fetch(
                          'https://jsonplaceholder.typicode.com/users/1');
                        return await response.text();
                    }
                    """).toString();
            
            System.out.println(response);
            
            assertThat(response).contains("Akos Mock User");
            browser.close();
        }
    }
}
