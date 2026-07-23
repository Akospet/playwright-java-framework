package com.akospeteri.playwright.visual;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import com.microsoft.playwright.Locator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VisualComparator {
    
    private static final Path BASELINE = Paths.get(
            "src",
            "test",
            "resources",
            "visual",
            "baseline",
            PlatformUtil.getPlatformFolder());
    private static final Path ACTUAL = Paths.get("target/visual/actual");
    private static final Path DIFF = Paths.get("target/visual/diff");
    
    public static void compare(Locator locator, String imageName) {
        
        try {
            
            Files.createDirectories(BASELINE);
            Files.createDirectories(ACTUAL);
            Files.createDirectories(DIFF);
            
            Path baselineImage = BASELINE.resolve(imageName + ".png");
            Path actualImage = ACTUAL.resolve(imageName + ".png");
            Path diffImage = DIFF.resolve(imageName + "-diff.png");
            
            locator.screenshot(new Locator.ScreenshotOptions().setPath(actualImage));
            
            if (!Files.exists(baselineImage)) {
                Files.copy(actualImage, baselineImage);
                
                System.out.println("------------------------------------------");
                System.out.println("Baseline created:");
                System.out.println(baselineImage.toAbsolutePath());
                System.out.println("------------------------------------------");
                
                return;
            }
            
            BufferedImage expected = ImageIO.read(baselineImage.toFile());
            BufferedImage actual = ImageIO.read(actualImage.toFile());
            
            ImageComparison comparison = new ImageComparison(expected, actual);
            
            comparison.setDifferenceRectangleColor(Color.RED);
            comparison.setPixelToleranceLevel(0.05);
            
            ImageComparisonResult result = comparison.compareImages();
            
            if (result.getImageComparisonState() != ImageComparisonState.MATCH) {
                
                ImageIO.write(
                        result.getResult(),
                        "png",
                        diffImage.toFile());
                
                throw new AssertionError("""
                        
                        ==========================================
                        VISUAL REGRESSION DETECTED
                        ==========================================
                        
                        Baseline:
                        %s
                        
                        Actual:
                        %s
                        
                        Diff:
                        %s
                        
                        ==========================================
                        """.formatted(
                        baselineImage.toAbsolutePath(),
                        actualImage.toAbsolutePath(),
                        diffImage.toAbsolutePath()
                ));
            }

            System.out.println("------------------------------------------");
            System.out.println("Visual comparison passed.");
            System.out.println("Platform : " + PlatformUtil.getPlatformFolder());
            System.out.println("Baseline : " + baselineImage);
            System.out.println("Actual   : " + actualImage);
            System.out.println("------------------------------------------");
            
        } catch (IOException e) {
            throw new UncheckedIOException("Visual comparison failed.", e);
        }
    }
}
