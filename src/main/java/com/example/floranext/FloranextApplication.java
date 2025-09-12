package com.example.floranext;

import com.example.floranext.runner.FloranextAutomationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

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
        
        // Get the automation runner and execute
        FloranextAutomationRunner runner = context.getBean(FloranextAutomationRunner.class);
        boolean success = runner.runAutomation();
        
        // Exit with appropriate code
        System.exit(success ? 0 : 1);
    }
}