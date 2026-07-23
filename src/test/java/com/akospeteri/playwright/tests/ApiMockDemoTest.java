package com.akospeteri.playwright.tests;

import com.akospeteri.playwright.server.EmbeddedTestServer;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

class ApiMockDemoTest {
    
    @Test
    void shouldMockApiResponse() {
        
        EmbeddedTestServer server = new EmbeddedTestServer();
        
        try (Playwright playwright = Playwright.create()) {

            server.start();

            Browser browser =
                    playwright.chromium().launch(
                            new BrowserType.LaunchOptions()
                                    .setHeadless(true));
            
            BrowserContext context = browser.newContext();
            
            Page page = context.newPage();
            page.onConsoleMessage(msg ->
                    System.out.println("CONSOLE: " + msg.text()));
            String mockResponse = Files.readString(Paths.get("src/test/resources/mock/fruits.json"));
            
            page.route("**/*", route -> {
                System.out.println(route.request().url());
                
                if (route.request().url().endsWith("/api/fruits")) {
                    route.fulfill(
                            new Route.FulfillOptions()
                                    .setStatus(200)
                                    .setContentType("application/json")
                                    .setBody(mockResponse));
                } else {
                    route.resume();
                }
            });
            
            page.navigate("http://localhost:8080/index.html");
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
        } finally {
            server.stop();
        }
    }
}
