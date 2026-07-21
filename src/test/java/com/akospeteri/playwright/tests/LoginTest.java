package com.akospeteri.playwright.tests;

import com.akospeteri.playwright.base.BaseTest;
import com.akospeteri.playwright.pages.InventoryPage;
import com.akospeteri.playwright.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginTest extends BaseTest {
    
    @Test
    void validUserCanLogin() {
        
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("standard_user", "secret_sauce");

        InventoryPage inventoryPage = new InventoryPage(page);

        assertThat(inventoryPage.isLoaded()).isTrue();
    }
}
