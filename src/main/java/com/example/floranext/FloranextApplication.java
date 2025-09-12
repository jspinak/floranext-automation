package com.example.floranext;

import com.example.floranext.runner.FloranextAutomationRunner;
import io.github.jspinak.brobot.automation.AutomationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Slf4j
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {
    "com.example.floranext",
    "io.github.jspinak.brobot"
})
public class FloranextApplication {
    
    public static void main(String[] args) {
        // Set system properties for proper initialization
        System.setProperty("java.awt.headless", "false");
        
        // Configure Spring Boot for GUI automation (not headless)
        SpringApplicationBuilder builder = new SpringApplicationBuilder(FloranextApplication.class);
        builder.headless(false);
        
        // Start the Spring context
        ConfigurableApplicationContext context = builder.run(args);
        
        // Use Brobot's AutomationRunner for graceful failure handling
        AutomationRunner automationRunner = context.getBean(AutomationRunner.class);
        FloranextAutomationRunner floranextRunner = context.getBean(FloranextAutomationRunner.class);
        
        // Run automation with proper error handling
        boolean success = automationRunner.run(
            floranextRunner::runAutomation,
            "Floranext Automation"
        );
        
        if (success) {
            log.info("✅ Floranext automation completed successfully!");
        } else {
            log.error("❌ Floranext automation failed - application continues running");
            // Note: Application does NOT exit on failure by default
            // This allows for cleanup, monitoring, or retry logic
        }
        
        // Optional: Keep application running for monitoring or scheduled tasks
        // Uncomment the following line to keep the application running:
        // while (true) { Thread.sleep(60000); }
        
        // Optional: Exit with code only if explicitly needed
        // Uncomment the following line to exit based on success:
        // System.exit(success ? 0 : 1);
    }
}