package com.akospeteri.playwright.auth;

import com.akospeteri.playwright.config.FrameworkConfig;
import com.akospeteri.playwright.pages.LoginPage;
import com.microsoft.playwright.Page;

public class AuthenticationManager {
    
    private AuthenticationManager() {
    }
    
    public static void createStorageState(Page page, FrameworkConfig config) {
        
        page.navigate(config.baseUrl());
        
        LoginPage loginPage = new LoginPage(page);
        
        loginPage.login(config.username(), config.password());
        
        loginPage.saveStorageState();
    }
}
