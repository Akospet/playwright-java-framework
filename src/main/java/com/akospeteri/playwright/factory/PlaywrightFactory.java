package com.akospeteri.playwright.factory;

import com.akospeteri.playwright.config.FrameworkConfig;
import com.microsoft.playwright.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaywrightFactory {
    
    private static final Logger LOG = LoggerFactory.getLogger(PlaywrightFactory.class);
    
    private static final ThreadLocal<Playwright> PLAYWRIGHT = new ThreadLocal<>();
    private static final ThreadLocal<Browser> BROWSER = new ThreadLocal<>();
    private static final ThreadLocal<BrowserContext> CONTEXT = new ThreadLocal<>();
    private static final ThreadLocal<Page> PAGE = new ThreadLocal<>();
    private static final ThreadLocal<String> TEST_NAME = new ThreadLocal<>();
    
    public static void create(FrameworkConfig config) {
        
        LOG.info("Starting Playwright");
        
        Playwright playwright = Playwright.create();
        
        try {
            Files.createDirectories(Paths.get("target/traces"));
            Files.createDirectories(getVideoDir());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        BrowserType browserType = switch (config.browser()) {
            case FIREFOX -> playwright.firefox();
            case WEBKIT -> playwright.webkit();
            case CHROMIUM -> playwright.chromium();
        };
        
        Browser browser = browserType.launch(new BrowserType.LaunchOptions()
                .setHeadless(config.headless())
                .setSlowMo(config.slowMo()));
        
        BrowserContext context = browser.newContext(
                new Browser.NewContextOptions()
                        .setViewportSize(1920, 1080)
                        .setRecordVideoDir(getVideoDir())
                        .setRecordVideoSize(1920, 1080));
        
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        Page page = context.newPage();
        
        PLAYWRIGHT.set(playwright);
        BROWSER.set(browser);
        CONTEXT.set(context);
        PAGE.set(page);
        
        LOG.info("{} browser started", config.browser());
    }
    
    public static void setTestName(String testName) {
        TEST_NAME.set(testName);
    }
    
    private static Path getVideoDir() {
        return Paths.get("target/videos");
    }
    
    public static Page page() {
        return PAGE.get();
    }
    
    public static void close() {
        
        LOG.info("Closing Playwright");
        
        try {
            
            if (CONTEXT.get() != null) {
                
                String traceName = TEST_NAME.get();
                
                if (traceName == null || traceName.isBlank()) {
                    traceName = "trace";
                }
                
                traceName = traceName.replaceAll("[^a-zA-Z0-9._-]", "_");
                
                CONTEXT.get().tracing().stop(
                        new Tracing.StopOptions()
                                .setPath(Paths.get("target/traces", traceName + ".zip")));
                
                CONTEXT.get().close();
            }
            
            if (BROWSER.get() != null) {
                BROWSER.get().close();
            }
            
            if (PLAYWRIGHT.get() != null) {
                PLAYWRIGHT.get().close();
            }
            
        } finally {
            
            CONTEXT.remove();
            BROWSER.remove();
            PAGE.remove();
            TEST_NAME.remove();
            PLAYWRIGHT.remove();
        }
    }
}
