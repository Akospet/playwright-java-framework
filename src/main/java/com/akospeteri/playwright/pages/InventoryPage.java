package com.akospeteri.playwright.pages;

import com.microsoft.playwright.Page;

public class InventoryPage {
    
    public static final String URL = "/inventory.html";
    private final Page page;

    public InventoryPage(Page page) {
        this.page = page;
    }

    public boolean isLoaded() {
        return page.locator("[data-test='inventory-container']").isVisible();
    }
}
