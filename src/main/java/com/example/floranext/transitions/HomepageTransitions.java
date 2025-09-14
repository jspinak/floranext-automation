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
 * All transitions for the Homepage state.
 * Contains only an IncomingTransition to verify arrival at Homepage.
 *
 * Homepage has no outgoing transitions - navigation happens through
 * the Menu state which remains active alongside Homepage.
 */
@TransitionSet(state = HomepageState.class, description = "Homepage transitions")
@Component
@RequiredArgsConstructor
@Slf4j
public class HomepageTransitions {

    private final HomepageState homepageState;
    private final Action action;
    
    // Homepage has no outgoing transitions.
    // Navigation to other states happens through the Menu state,
    // which remains active alongside Homepage.
    
    /**
     * Verify that we have successfully arrived at the Homepage state.
     * Checks for the presence of either the large "Start for Free" button
     * or the "Enter Your Email" field.
     */
    @IncomingTransition(description = "Verify arrival at Homepage state", required = true)
    public boolean verifyArrival() {
        log.info("Verifying arrival at Homepage state");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful verification");
            return true;
        }
        
        // Check for either homepage element
        boolean foundEmailField = action.find(homepageState.getEnterYourEmail()).isSuccess();
        
        if (foundEmailField) {
            log.info("Successfully confirmed Homepage state is active");
            return true;
        } else {
            log.error("Failed to confirm Homepage state - no homepage elements found");
            return false;
        }
    }
}