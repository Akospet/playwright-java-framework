package com.akospeteri.playwright.utils;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.ScreenshotType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ScreenshotUtil {
    
    private static final Logger LOG = LoggerFactory.getLogger(ScreenshotUtil.class);
    
    public static void capture(Page page, String testName) {
        try {
            Path directory = Paths.get("target", "screenshots");
            Files.createDirectories(directory);
            Path file = directory.resolve(testName + ".png");
            
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(file)
                    .setType(ScreenshotType.PNG)
                    .setFullPage(true));
            
            LOG.info("Screenshot saved: {}", file);
        } catch (Exception e) {
            LOG.error("Unable to save screenshot", e);
        }
    }
}
