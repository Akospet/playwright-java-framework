package com.akospeteri.playwright.config;

import com.akospeteri.playwright.model.Browser;

public record FrameworkConfig (
        String baseUrl,
        String username,
        String password,
        Browser browser,
        boolean headless,
        int slowMo,
        boolean useStorageState
) {}
