import io.github.jspinak.brobot.automation.AutomationRunner;
import io.github.jspinak.brobot.config.automation.AutomationConfig;

/**
 * Simple test to demonstrate AutomationRunner graceful failure handling.
 */
public class TestAutomationRunner {
    
    public static void main(String[] args) {
        System.out.println("=== Testing AutomationRunner Graceful Failure Handling ===\n");
        
        // Create AutomationRunner with config
        AutomationRunner runner = new AutomationRunner();
        AutomationConfig config = runner.getConfig();
        
        // Configure for demonstration
        config.setMaxRetries(2);
        config.setRetryDelayMs(1000);
        config.setExitOnFailure(false); // Don't exit on failure
        config.setLogStackTraces(false);
        
        System.out.println("Configuration:");
        System.out.println("- Max retries: " + config.getMaxRetries());
        System.out.println("- Retry delay: " + config.getRetryDelayMs() + "ms");
        System.out.println("- Exit on failure: " + config.isExitOnFailure());
        System.out.println("\nRunning automation that will fail...\n");
        
        // Define a task that always fails
        AutomationRunner.AutomationTask failingTask = () -> {
            System.out.println("  Executing automation task...");
            System.out.println("  Task failed!");
            return false; // Simulate failure
        };
        
        // Run the task - it will retry and handle failure gracefully
        boolean success = runner.run(failingTask, "Demo Automation");
        
        System.out.println("\n=== Results ===");
        System.out.println("Automation success: " + success);
        System.out.println("Application still running (not crashed)!");
        System.out.println("\nThis demonstrates that:");
        System.out.println("1. The automation failed");
        System.out.println("2. It retried " + config.getMaxRetries() + " times");
        System.out.println("3. The application didn't crash with System.exit()");
        System.out.println("4. You can handle the failure gracefully");
        
        // Now demonstrate a successful task
        System.out.println("\n=== Running successful automation ===\n");
        
        AutomationRunner.AutomationTask successTask = () -> {
            System.out.println("  Executing automation task...");
            System.out.println("  Task succeeded!");
            return true;
        };
        
        success = runner.run(successTask, "Successful Demo");
        System.out.println("\nAutomation success: " + success);
        System.out.println("\n=== Test Complete - Application continues running ===");
    }
}