package com.akospeteri.playwright.config;

import com.akospeteri.playwright.model.Browser;

public record FrameworkConfig (
        String baseUrl,
        Browser browser,
        boolean headless,
        int slowMo,
        boolean useStorageState
) {
    
    public FrameworkConfig withoutStorageState() {
        return new FrameworkConfig(
                baseUrl,
                browser,
                headless,
                slowMo,
                false
        );
    }
    
    public FrameworkConfig withStorageState() {
        return new FrameworkConfig(
                baseUrl,
                browser,
                headless,
                slowMo,
                true
        );
    }
}
