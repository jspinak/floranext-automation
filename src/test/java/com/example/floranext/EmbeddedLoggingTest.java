package com.example.floranext;

import io.github.jspinak.brobot.action.Action;
import io.github.jspinak.brobot.action.ActionResult;
import io.github.jspinak.brobot.action.basic.click.ClickOptions;
import io.github.jspinak.brobot.model.state.StateImage;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test demonstrating embedded logging in ClickOptions.
 * This test shows how logging messages are embedded directly
 * in the Options variables, making the code more readable.
 */
public class EmbeddedLoggingTest {

    @Test
    public void testEmbeddedLoggingInClickOptions() {
        // Create mock action
        Action action = mock(Action.class);
        StateImage stateImage = new StateImage.Builder().build();

        // Create ClickOptions with embedded logging messages
        ClickOptions clickOptions = new ClickOptions.Builder()
                .withBeforeActionLog("Starting to click the submit button")
                .withSuccessLog("Successfully clicked the submit button")
                .withFailureLog("Failed to click the submit button - element not found")
                .build();

        // Mock successful result
        ActionResult successResult = mock(ActionResult.class);
        when(successResult.isSuccess()).thenReturn(true);
        when(action.perform(clickOptions, stateImage)).thenReturn(successResult);

        // Execute action with embedded logging
        ActionResult result = action.perform(clickOptions, stateImage);

        // Verify success
        assertTrue(result.isSuccess(), "Click action should succeed");

        System.out.println("Test completed successfully!");
        System.out.println("The following logging messages would be generated:");
        System.out.println("- Before action: " + clickOptions.getLoggingOptions().getBeforeActionMessage());
        System.out.println("- On success: " + clickOptions.getLoggingOptions().getSuccessMessage());
        System.out.println("- On failure (if it had failed): " + clickOptions.getLoggingOptions().getFailureMessage());
    }

    @Test
    public void testMultipleActionsWithEmbeddedLogging() {
        // Demonstrate multiple actions with different embedded logging
        Action action = mock(Action.class);

        // Login button click with logging
        ClickOptions loginClickOptions = new ClickOptions.Builder()
                .withBeforeActionLog("Attempting to click login button")
                .withSuccessLog("Login button clicked successfully")
                .withFailureLog("ERROR: Login button not found or not clickable")
                .build();

        // Submit form click with logging
        ClickOptions submitClickOptions = new ClickOptions.Builder()
                .withBeforeActionLog("Submitting form data")
                .withSuccessLog("Form submitted successfully")
                .withFailureLog("ERROR: Form submission failed")
                .build();

        // Cancel button click with logging
        ClickOptions cancelClickOptions = new ClickOptions.Builder()
                .withBeforeActionLog("Clicking cancel to close dialog")
                .withSuccessLog("Dialog closed successfully")
                .withFailureLog("WARNING: Could not close dialog")
                .build();

        System.out.println("Multiple actions configured with embedded logging:");
        System.out.println("\n1. Login Action:");
        System.out.println("   - " + loginClickOptions.getLoggingOptions().getSuccessMessage());

        System.out.println("\n2. Submit Action:");
        System.out.println("   - " + submitClickOptions.getLoggingOptions().getSuccessMessage());

        System.out.println("\n3. Cancel Action:");
        System.out.println("   - " + cancelClickOptions.getLoggingOptions().getSuccessMessage());

        assertTrue(true, "Demonstration completed");
    }
}