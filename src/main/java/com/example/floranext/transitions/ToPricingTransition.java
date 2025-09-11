package com.example.floranext.transitions;

import com.example.floranext.states.PricingState;
import io.github.jspinak.brobot.actions.Action;
import io.github.jspinak.brobot.actions.ActionResult;
import io.github.jspinak.brobot.manageStates.Transition;
import lombok.RequiredArgsConstructor;
import lombok.Slf4j;

/**
 * ToTransition for PricingState.
 * This transition verifies we have successfully arrived at the Pricing state,
 * regardless of which state we came from.
 */
@Transition(to = PricingState.class)
@RequiredArgsConstructor
@Slf4j
public class ToPricingTransition {
    
    private final PricingState pricingState;
    private final Action action;
    
    public boolean execute() {
        log.info("Verifying arrival at Pricing state");
        
        // Verify we're in the pricing state by finding the start for free button
        ActionResult findResult = action.find(pricingState.getStartForFree());
        
        if (findResult.isSuccess()) {
            log.info("Successfully confirmed Pricing state is active");
            return true;
        } else {
            log.error("Failed to confirm Pricing state - start for free button not found");
            return false;
        }
    }
}