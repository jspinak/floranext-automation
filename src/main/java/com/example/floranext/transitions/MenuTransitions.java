package com.example.floranext.transitions;

import com.example.floranext.states.MenuState;
import com.example.floranext.states.PricingState;
import com.example.floranext.states.HomepageState;
import io.github.jspinak.brobot.action.Action;
import io.github.jspinak.brobot.annotations.OutgoingTransition;
import io.github.jspinak.brobot.annotations.IncomingTransition;
import io.github.jspinak.brobot.annotations.TransitionSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * All transitions for the Menu state.
 * Contains:
 * - An IncomingTransition to verify arrival at Menu
 * - OutgoingTransitions that go FROM Menu TO other states
 *
 * This pattern is cleaner because the outgoing transitions use Menu's images,
 * creating better cohesion with only the MenuState as a dependency.
 */
@TransitionSet(state = MenuState.class, description = "Menu navigation transitions")
@Component
@RequiredArgsConstructor
@Slf4j
public class MenuTransitions {

    private final MenuState menuState;
    private final Action action;
    

    /**
     * Navigate from Menu to Homepage by clicking the homepage/logo link.
     */
    @OutgoingTransition(to = HomepageState.class, priority = 1, description = "Navigate from Menu to Homepage")
    public boolean toHomepage() {
        log.info("Navigating from Menu to Homepage");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful navigation");
            return true;
        }
        // Click the homepage link using Menu's image
        return action.click(menuState.getHomepage()).isSuccess();
    }

    /**
     * Navigate from Menu to Pricing by clicking the pricing menu item.
     */
    @OutgoingTransition(to = PricingState.class, priority = 2, description = "Navigate from Menu to Pricing")
    public boolean toPricing() {
        log.info("Navigating from Menu to Pricing");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful navigation");
            return true;
        }
        // Click the pricing button using Menu's image
        return action.click(menuState.getPricing()).isSuccess();
    }
    
    /**
     * Verify that we have successfully arrived at the Menu state.
     * Checks for presence of key menu elements.
     */
    @IncomingTransition(description = "Verify arrival at Menu state")
    public boolean verifyArrival() {
        log.info("Verifying arrival at Menu state");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful verification");
            return true;
        }
        // Check for presence of menu-specific elements
        boolean foundIcon = action.find(menuState.getHomepage()).isSuccess();
        boolean foundPricing = action.find(menuState.getPricing()).isSuccess();
        
        if (foundIcon || foundPricing) {
            log.info("Successfully confirmed Menu state is active");
            return true;
        } else {
            log.error("Failed to confirm Menu state - menu elements not found");
            return false;
        }
    }
}