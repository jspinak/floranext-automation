package com.example.floranext.runner;

import com.example.floranext.states.HomepageState;
import com.example.floranext.states.PricingState;
import io.github.jspinak.brobot.actions.Action;
import io.github.jspinak.brobot.actions.ActionResult;
import io.github.jspinak.brobot.manageStates.Navigation;
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
    private final Navigation navigation;
    
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
        log.info("Step 1: Navigating to pricing page");
        if (!navigation.goToState("Pricing")) {
            log.error("Failed to navigate to pricing page");
            return false;
        }
        log.info("Successfully navigated to pricing state");
        
        // Step 2: Click on start for free button on pricing page
        log.info("Step 2: Clicking start for free button on pricing page");
        ActionResult pricingClick = action.click(pricingState.getStartForFree());
        if (!pricingClick.isSuccess()) {
            log.error("Failed to click start for free button on pricing page");
            return false;
        }
        log.info("Successfully clicked start for free button on pricing page");
        
        // Step 3: Navigate to homepage using Brobot's navigation system
        log.info("Step 3: Navigating to homepage");
        if (!navigation.goToState("Homepage")) {
            log.error("Failed to navigate to homepage");
            return false;
        }
        log.info("Successfully navigated to homepage state");
        
        // Step 4: Click on enter your email field
        log.info("Step 4: Clicking enter your email field");
        ActionResult emailClick = action.click(homepageState.getEnterYourEmail());
        if (!emailClick.isSuccess()) {
            log.error("Failed to click enter your email field");
            return false;
        }
        log.info("Successfully clicked enter your email field");
        
        log.info("Automation sequence completed successfully!");
        return true;
    }
}