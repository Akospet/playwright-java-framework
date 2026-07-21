package com.akospeteri.playwright.base;

import com.akospeteri.playwright.config.ConfigReader;
import com.akospeteri.playwright.config.FrameworkConfig;
import com.akospeteri.playwright.factory.PlaywrightFactory;
import com.akospeteri.playwright.listeners.ScreenshotExtension;
import com.microsoft.playwright.Page;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(ScreenshotExtension.class)
public abstract class BaseTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(BaseTest.class);
    protected Page page;
    protected String testName;
    
    @BeforeEach
    void setup(TestInfo testInfo) {

        testName = testInfo.getDisplayName();

        LOG.info("========== START TEST ==========");
        FrameworkConfig config = new ConfigReader().load();
        PlaywrightFactory.create(config);
        
        page = PlaywrightFactory.page();
        page.navigate(config.baseUrl());
    }
    
    @AfterEach
    void teardown() {
        PlaywrightFactory.close();
        LOG.info("========== END TEST ==========");
    }
}
