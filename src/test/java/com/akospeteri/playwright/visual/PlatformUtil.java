package com.akospeteri.playwright.visual;

import java.util.Locale;

public final class PlatformUtil {
    
    private PlatformUtil() {
    }
    
    public static String getPlatformFolder() {
        
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        
        if (os.contains("mac")) {
            return "mac";
        }
        
        if (os.contains("win")) {
            return "windows";
        }
        
        return "linux";
    }
}
