package com.example.floranext.transitions;

import com.example.floranext.states.MenuState;
import com.example.floranext.states.PricingState;
import com.example.floranext.states.HomepageState;
import io.github.jspinak.brobot.action.Action;
import io.github.jspinak.brobot.annotations.FromTransition;
import io.github.jspinak.brobot.annotations.ToTransition;
import io.github.jspinak.brobot.annotations.TransitionSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * All transitions for the Homepage state.
 * Contains FromTransitions from other states TO Homepage,
 * and a ToTransition to verify arrival at Homepage.
 */
@TransitionSet(state = HomepageState.class, description = "Homepage transitions")
@Component
@RequiredArgsConstructor
@Slf4j
public class HomepageTransitions {
    
    private final MenuState menuState;
    private final PricingState pricingState;
    private final HomepageState homepageState;
    private final Action action;
    
    /**
     * Navigate from Menu to Homepage by clicking the Floranext icon.
     */
    @FromTransition(from = MenuState.class, priority = 1, description = "Navigate from Menu to Homepage")
    public boolean fromMenu() {
        log.info("Navigating from Menu to Homepage");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful navigation");
            return true;
        }
        return action.click(menuState.getFloranextIcon()).isSuccess();
    }
    
    /**
     * Navigate from Pricing to Homepage.
     * This might involve clicking a home button or logo.
     */
    @FromTransition(from = PricingState.class, priority = 2, description = "Navigate from Pricing to Homepage")
    public boolean fromPricing() {
        log.info("Navigating from Pricing to Homepage");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful navigation");
            return true;
        }
        // Assuming we can click the Floranext icon from pricing page
        // You might need to add navigation elements to PricingState
        return action.click(menuState.getFloranextIcon()).isSuccess();
    }
    
    /**
     * Verify that we have successfully arrived at the Homepage state.
     * Checks for the presence of either the large "Start for Free" button
     * or the "Enter Your Email" field.
     */
    @ToTransition(description = "Verify arrival at Homepage state", required = true)
    public boolean verifyArrival() {
        log.info("Verifying arrival at Homepage state");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful verification");
            return true;
        }
        
        // Check for either homepage element
        boolean foundBigButton = action.find(homepageState.getStartForFreeBig()).isSuccess();
        boolean foundEmailField = action.find(homepageState.getEnterYourEmail()).isSuccess();
        
        if (foundBigButton || foundEmailField) {
            log.info("Successfully confirmed Homepage state is active");
            return true;
        } else {
            log.error("Failed to confirm Homepage state - no homepage elements found");
            return false;
        }
    }
}