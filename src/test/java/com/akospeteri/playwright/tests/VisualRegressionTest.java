package com.akospeteri.playwright.tests;

import com.akospeteri.playwright.base.BaseTest;
import com.akospeteri.playwright.visual.VisualComparator;
import org.junit.jupiter.api.Test;

class VisualRegressionTest extends BaseTest {
    
    @Test
    void inventoryShouldMatchBaseline() {
        
        page.navigate(config.baseUrl() + "/inventory.html");
        System.out.println("URL : " + page.url());
        System.out.println(page.content());
        VisualComparator.compare(page.locator(".inventory_list"), "inventory-list");
        
    }
}
