package com.akospeteri.playwright.config;

import com.akospeteri.playwright.model.Browser;

public record FrameworkConfig (
        String baseUrl,
        Browser browser,
        boolean headless,
        int slowMo
) {}
