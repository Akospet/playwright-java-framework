package com.akospeteri.playwright.tests;

import com.akospeteri.playwright.base.BaseTest;
import com.akospeteri.playwright.factory.PlaywrightFactory;
import com.akospeteri.playwright.pages.InventoryPage;
import com.akospeteri.playwright.pages.LoginPage;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoginTest extends BaseTest {
    
    @Override
    protected boolean requiresAuthentication() {
        return false;
    }
    
    @Test
    void validUserCanLogin() {
        
        page.navigate(config.baseUrl());
        LoginPage loginPage = new LoginPage(page);
        loginPage.login("standard_user", "secret_sauce");

        loginPage.saveStorageState();
        InventoryPage inventoryPage = new InventoryPage(page);
        page.context().cookies().forEach(cookie -> {
            System.out.println("Name   : " + cookie.name);
            System.out.println("Value  : " + cookie.value);
            System.out.println("Domain : " + cookie.domain);
            System.out.println("Path   : " + cookie.path);
            System.out.println("Expires: " + cookie.expires);
            System.out.println("----------------------------");
        });
        assertThat(inventoryPage.isLoaded()).isTrue();
    }
}
