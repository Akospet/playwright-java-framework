package com.akospeteri.playwright.tests;

import com.akospeteri.playwright.base.BaseTest;
import com.akospeteri.playwright.factory.PlaywrightFactory;
import com.akospeteri.playwright.pages.InventoryPage;
import com.akospeteri.playwright.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginTest extends BaseTest {
    
    @Override
    protected boolean useStorageState() {
        return false;
    }
    
    @Test
    void validUserCanLogin() {
        
        page.navigate(config.baseUrl());
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("standard_user", "secret_sauce");

        loginPage.saveStorageState();
        InventoryPage inventoryPage = new InventoryPage(page);

        assertThat(inventoryPage.isLoaded()).isTrue();
    }
}
