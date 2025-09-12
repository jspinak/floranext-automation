package com.example.floranext.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.github.jspinak.brobot.capture.CrossPlatformPhysicalCapture;
import io.github.jspinak.brobot.capture.BrobotCaptureService;

/**
 * Configuration for mock mode to avoid JavaCV initialization issues.
 */
@Configuration
@ConditionalOnProperty(name = "brobot.mock.enabled", havingValue = "true")
public class MockModeConfiguration {
    
    /**
     * Provides a no-op capture service for mock mode.
     */
    @Bean
    @Primary
    public CrossPlatformPhysicalCapture mockCrossPlatformPhysicalCapture() {
        return new CrossPlatformPhysicalCapture() {
            @Override
            public void startCapture() {
                // No-op in mock mode
            }
            
            @Override
            public void stopCapture() {
                // No-op in mock mode
            }
        };
    }
}