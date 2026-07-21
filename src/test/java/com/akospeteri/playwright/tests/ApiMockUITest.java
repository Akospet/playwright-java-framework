package com.akospeteri.playwright.tests;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

class ApiMockDemoTest {
    
    @Test
    void shouldMockApiResponse() {
        
        try (Playwright playwright = Playwright.create()) {
            
            Browser browser =
                    playwright.chromium().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(true));
            
            BrowserContext context = browser.newContext();
            
            Page page = context.newPage();
            page.onConsoleMessage(msg ->
                    System.out.println("CONSOLE: " + msg.text()));
            String mockResponse = Files.readString(Paths.get("src/test/resources/mock/fruits.json"));
            
            page.route("**/api/fruits", route -> {
                
                System.out.println(">>> API intercepted");
                
                route.fulfill(
                        new Route.FulfillOptions()
                                .setStatus(200)
                                .setContentType("application/json")
                                .setBody(mockResponse));
            });
            
            page.navigate(Paths.get("src/test/resources/demo/index.html")
                    .toUri().toString());
            page.waitForSelector("#fruits li");
            Locator fruits = page.locator("#fruits li");
            System.out.println(page.content());
            assertThat(fruits.count()).isEqualTo(3);
            assertThat(fruits.nth(0).textContent()).isEqualTo("Apple");
            assertThat(fruits.nth(1).textContent()).isEqualTo("Banana");
            assertThat(fruits.nth(2).textContent()).isEqualTo("Kiwi");
            
            browser.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }
    
}
