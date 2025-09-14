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
 * All transitions for the Pricing state.
 * Contains only an IncomingTransition to verify arrival at Pricing.
 *
 * Pricing has no outgoing transitions - navigation happens through
 * the Menu state which remains active alongside Pricing.
 */
@TransitionSet(state = PricingState.class, description = "Pricing page transitions")
@Component
@RequiredArgsConstructor
@Slf4j
public class PricingTransitions {

    private final PricingState pricingState;
    private final Action action;
    
    // Pricing has no outgoing transitions.
    // Navigation to other states happens through the Menu state,
    // which remains active alongside Pricing.
    
    /**
     * Verify that we have successfully arrived at the Pricing state.
     * Checks for the presence of the "Start for Free" button.
     */
    @IncomingTransition(description = "Verify arrival at Pricing state", required = true)
    public boolean verifyArrival() {
        log.info("Verifying arrival at Pricing state");
        // In mock mode, just return true for testing
        if (io.github.jspinak.brobot.config.core.FrameworkSettings.mock) {
            log.info("Mock mode: simulating successful verification");
            return true;
        }
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