package com.akospeteri.playwright.pages;

import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class LoginPage {
    
    private final Page page;
    
    public LoginPage(Page page) {
        this.page = page;
    }
    
    public LoginPage enterUsername(String username) {
        page.locator("[data-test='username']").fill(username);
        return this;
    }
    
    public LoginPage enterPassword(String password) {
        page.locator("[data-test='password']").fill(password);
        return this;
    }
    
    public void clickLogin() {
        page.locator("[data-test='login-button']").click();
    }
    
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }
    
    public void saveStorageState() {
        
        page.context().storageState(
                new BrowserContext.StorageStateOptions()
                        .setPath(Paths.get("src/test/resources/auth/storageState.json")));
    }
}
