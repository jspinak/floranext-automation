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

/**
 * All transitions for the Menu state.
 * Contains FromTransitions from other states TO Menu,
 * and a ToTransition to verify arrival at Menu.
 */
@TransitionSet(state = MenuState.class, description = "Menu navigation transitions")
@RequiredArgsConstructor
@Slf4j
public class MenuTransitions {
    
    private final MenuState menuState;
    private final PricingState pricingState;
    private final HomepageState homepageState;
    private final Action action;
    
    
    /**
     * Navigate from Pricing back to Menu.
     * This might involve clicking a back button or menu icon.
     */
    @FromTransition(from = PricingState.class, description = "Navigate from Pricing to Menu")
    public boolean fromPricing() {
        log.info("Navigating from Pricing to Menu");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful navigation");
            return true;
        }
        // Assuming there's a menu icon or back button on the pricing page
        // You might need to add a menuIcon field to PricingState
        return action.click(menuState.getFloranextIcon()).isSuccess();
    }
    
    /**
     * Navigate from Homepage back to Menu.
     * This might involve clicking a menu toggle button.
     */
    @FromTransition(from = HomepageState.class, description = "Navigate from Homepage to Menu")
    public boolean fromHomepage() {
        log.info("Navigating from Homepage to Menu");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful navigation");
            return true;
        }
        // Assuming there's a menu toggle on the homepage
        return action.click(menuState.getToggleMenu()).isSuccess();
    }
    
    /**
     * Verify that we have successfully arrived at the Menu state.
     * Checks for presence of key menu elements.
     */
    @ToTransition(description = "Verify arrival at Menu state")
    public boolean verifyArrival() {
        log.info("Verifying arrival at Menu state");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful verification");
            return true;
        }
        // Check for presence of menu-specific elements
        boolean foundIcon = action.find(menuState.getFloranextIcon()).isSuccess();
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