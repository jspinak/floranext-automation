package com.example.floranext.runner;

import com.example.floranext.states.HomepageState;
import com.example.floranext.states.PricingState;
import io.github.jspinak.brobot.action.Action;
import io.github.jspinak.brobot.action.ActionResult;
import io.github.jspinak.brobot.action.basic.click.ClickOptions;
import io.github.jspinak.brobot.navigation.transition.StateNavigator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Main automation service for Floranext.
 * Can be called from main application or used as a service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FloranextAutomationRunner {
    
    private final Action action;
    private final PricingState pricingState;
    private final HomepageState homepageState;
    private final StateNavigator stateNavigator;
    
    /**
     * Execute the main automation sequence.
     * @return true if automation completed successfully, false otherwise
     */
    public boolean runAutomation() {
        log.info("Starting Floranext automation...");
        
        try {
            // Execute the automation sequence
            boolean success = performAutomation();
            
            if (success) {
                log.info("Floranext automation completed successfully!");
            } else {
                log.error("Floranext automation failed");
            }
            
            return success;
        } catch (Exception e) {
            log.error("Error during automation execution", e);
            return false;
        }
    }
    
    private boolean performAutomation() {
        log.info("Starting automation sequence");

        // Step 1: Navigate to pricing page using Brobot's navigation system
        // Note: StateNavigator doesn't support custom options, so we keep manual logging for navigation
        log.info("Step 1: Navigating to pricing page");
        if (!stateNavigator.openState("PricingState")) {
            log.error("Failed to navigate to pricing page");
            return false;
        }
        log.info("Successfully navigated to pricing state");

        // Step 2: Click on start for free button on pricing page with embedded logging
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: Simulating click on start for free button");
        } else {
            ClickOptions pricingClickOptions = new ClickOptions.Builder()
                    .withBeforeActionLog("Step 2: Clicking start for free button on pricing page")
                    .withSuccessLog("Successfully clicked start for free button on pricing page")
                    .withFailureLog("Failed to click start for free button on pricing page")
                    .build();

            ActionResult pricingClick = action.perform(pricingClickOptions, pricingState.getStartForFree());
            if (!pricingClick.isSuccess()) {
                return false;
            }
        }

        // Step 3: Navigate to homepage using Brobot's navigation system
        // Note: StateNavigator doesn't support custom options, so we keep manual logging for navigation
        log.info("Step 3: Navigating to homepage");
        if (!stateNavigator.openState("HomepageState")) {
            log.error("Failed to navigate to homepage");
            return false;
        }
        log.info("Successfully navigated to homepage state");

        // Step 4: Click on enter your email field with embedded logging
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: Simulating click on email field");
        } else {
            ClickOptions emailClickOptions = new ClickOptions.Builder()
                    .withBeforeActionLog("Step 4: Clicking enter your email field")
                    .withSuccessLog("Successfully clicked enter your email field")
                    .withFailureLog("Failed to click enter your email field")
                    .build();

            ActionResult emailClick = action.perform(emailClickOptions, homepageState.getEnterYourEmail());
            if (!emailClick.isSuccess()) {
                return false;
            }
        }

        log.info("Automation sequence completed successfully!");
        return true;
    }
}