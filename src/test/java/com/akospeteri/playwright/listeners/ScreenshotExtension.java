package com.akospeteri.playwright.listeners;

import com.akospeteri.playwright.factory.PlaywrightFactory;
import com.akospeteri.playwright.utils.ScreenshotUtil;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ScreenshotExtension implements AfterTestExecutionCallback {
    
    @Override
    public void afterTestExecution(ExtensionContext context) {
        
        if (context.getExecutionException().isPresent()) {
            ScreenshotUtil.capture(
                    PlaywrightFactory.page(),
                    context.getDisplayName());
        }
    }
}
