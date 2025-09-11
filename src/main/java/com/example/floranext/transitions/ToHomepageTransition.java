package com.example.floranext.transitions;

import com.example.floranext.states.HomepageState;
import io.github.jspinak.brobot.actions.Action;
import io.github.jspinak.brobot.actions.ActionResult;
import io.github.jspinak.brobot.actions.actionConfig.ConditionalActionChain;
import io.github.jspinak.brobot.collection.StateImageCollection;
import io.github.jspinak.brobot.manageStates.Transition;
import lombok.RequiredArgsConstructor;
import lombok.Slf4j;

/**
 * ToTransition for HomepageState.
 * This transition verifies we have successfully arrived at the Homepage state,
 * regardless of which state we came from.
 */
@Transition(to = HomepageState.class)
@RequiredArgsConstructor
@Slf4j
public class ToHomepageTransition {
    
    private final HomepageState homepageState;
    private final Action action;
    
    public boolean execute() {
        log.info("Verifying arrival at Homepage state");
        
        // Create a collection with both homepage elements
        StateImageCollection homepageElements = new StateImageCollection.Builder()
            .addStateImages(
                homepageState.getStartForFreeBig(),
                homepageState.getEnterYourEmail()
            )
            .build();
        
        // Verify we're in the homepage by finding either element
        ActionResult result = ConditionalActionChain
            .findAny(homepageElements)
            .perform(action, homepageElements);
            
        if (result.isSuccess()) {
            log.info("Successfully confirmed Homepage state is active");
            return true;
        } else {
            log.error("Failed to confirm Homepage state - no homepage elements found");
            return false;
        }
    }
}