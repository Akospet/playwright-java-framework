package com.akospeteri.playwright.config;

import com.akospeteri.playwright.model.Browser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    
    public FrameworkConfig load() {
        
        Properties properties = new Properties();
        
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            
            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }
            
            properties.load(input);
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        return new FrameworkConfig(
                properties.getProperty("base.url"),
                Browser.valueOf(properties.getProperty("browser").toUpperCase()),
                Boolean.parseBoolean(properties.getProperty("headless")),
                Integer.parseInt(properties.getProperty("slowmo"))
        );
    }
    
}
