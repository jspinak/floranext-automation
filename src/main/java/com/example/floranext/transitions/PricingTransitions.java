package com.example.floranext.transitions;

import com.example.floranext.states.MenuState;
import com.example.floranext.states.PricingState;
import com.example.floranext.states.HomepageState;
import io.github.jspinak.brobot.actions.Action;
import io.github.jspinak.brobot.annotations.FromTransition;
import io.github.jspinak.brobot.annotations.ToTransition;
import io.github.jspinak.brobot.annotations.TransitionSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * All transitions for the Pricing state.
 * Contains FromTransitions from other states TO Pricing,
 * and a ToTransition to verify arrival at Pricing.
 */
@TransitionSet(state = PricingState.class, description = "Pricing page transitions")
@RequiredArgsConstructor
@Slf4j
public class PricingTransitions {
    
    private final MenuState menuState;
    private final PricingState pricingState;
    private final HomepageState homepageState;
    private final Action action;
    
    /**
     * Navigate from Menu to Pricing by clicking the pricing menu item.
     */
    @FromTransition(from = MenuState.class, priority = 1, description = "Navigate from Menu to Pricing")
    public boolean fromMenu() {
        log.info("Navigating from Menu to Pricing");
        return action.click(menuState.getPricing()).isSuccess();
    }
    
    /**
     * Navigate from Homepage to Pricing.
     * This might involve first opening the menu, then clicking pricing.
     */
    @FromTransition(from = HomepageState.class, priority = 2, description = "Navigate from Homepage to Pricing")
    public boolean fromHomepage() {
        log.info("Navigating from Homepage to Pricing");
        // First ensure menu is visible
        // Then click pricing
        // This is a simplified version - you might need more complex logic
        return action.click(menuState.getPricing()).isSuccess();
    }
    
    /**
     * Verify that we have successfully arrived at the Pricing state.
     * Checks for the presence of the "Start for Free" button.
     */
    @ToTransition(description = "Verify arrival at Pricing state", required = true)
    public boolean verifyArrival() {
        log.info("Verifying arrival at Pricing state");
        boolean found = action.find(pricingState.getStartForFree()).isSuccess();
        
        if (found) {
            log.info("Successfully confirmed Pricing state is active");
            return true;
        } else {
            log.error("Failed to confirm Pricing state - start for free button not found");
            return false;
        }
    }
}