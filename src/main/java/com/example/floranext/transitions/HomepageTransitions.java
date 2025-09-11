package com.example.floranext.transitions;

import com.example.floranext.states.MenuState;
import com.example.floranext.states.PricingState;
import com.example.floranext.states.HomepageState;
import io.github.jspinak.brobot.actions.Action;
import io.github.jspinak.brobot.actions.actionConfig.ConditionalActionChain;
import io.github.jspinak.brobot.collection.StateImageCollection;
import io.github.jspinak.brobot.annotations.FromTransition;
import io.github.jspinak.brobot.annotations.ToTransition;
import io.github.jspinak.brobot.annotations.TransitionSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * All transitions for the Homepage state.
 * Contains FromTransitions from other states TO Homepage,
 * and a ToTransition to verify arrival at Homepage.
 */
@TransitionSet(state = HomepageState.class, description = "Homepage transitions")
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
        return action.click(menuState.getFloranextIcon()).isSuccess();
    }
    
    /**
     * Navigate from Pricing to Homepage.
     * This might involve clicking a home button or logo.
     */
    @FromTransition(from = PricingState.class, priority = 2, description = "Navigate from Pricing to Homepage")
    public boolean fromPricing() {
        log.info("Navigating from Pricing to Homepage");
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
        
        // Create a collection with both homepage elements
        StateImageCollection homepageElements = new StateImageCollection.Builder()
            .addStateImages(
                homepageState.getStartForFreeBig(),
                homepageState.getEnterYourEmail()
            )
            .build();
        
        // Use ConditionalActionChain to find either element
        boolean found = ConditionalActionChain
            .findAny(homepageElements)
            .perform(action, homepageElements)
            .isSuccess();
            
        if (found) {
            log.info("Successfully confirmed Homepage state is active");
            return true;
        } else {
            log.error("Failed to confirm Homepage state - no homepage elements found");
            return false;
        }
    }
}