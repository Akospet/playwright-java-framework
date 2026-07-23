package com.akospeteri.playwright.tests;

import com.akospeteri.playwright.base.BaseTest;
import com.akospeteri.playwright.pages.InventoryPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InventoryTest extends BaseTest {
    
    @Test
    void userIsAlreadyLoggedIn() {
        page.navigate(config.baseUrl() + InventoryPage.URL);
        assertThat(page.url()).contains("inventory");
    }
}
