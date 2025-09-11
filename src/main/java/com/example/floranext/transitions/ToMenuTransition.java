package com.example.floranext.transitions;

import com.example.floranext.states.MenuState;
import io.github.jspinak.brobot.actions.Action;
import io.github.jspinak.brobot.actions.ActionResult;
import io.github.jspinak.brobot.actions.actionConfig.ConditionalActionChain;
import io.github.jspinak.brobot.collection.StateImageCollection;
import io.github.jspinak.brobot.manageStates.Transition;
import lombok.RequiredArgsConstructor;
import lombok.Slf4j;

/**
 * ToTransition for MenuState.
 * This transition verifies we have successfully arrived at the Menu state,
 * regardless of which state we came from.
 */
@Transition(to = MenuState.class)
@RequiredArgsConstructor
@Slf4j
public class ToMenuTransition {
    
    private final MenuState menuState;
    private final Action action;
    
    public boolean execute() {
        log.info("Verifying arrival at Menu state");
        
        // Create a collection with key menu elements
        StateImageCollection menuElements = new StateImageCollection.Builder()
            .addStateImages(
                menuState.getFloranextIcon(),
                menuState.getPricing(),
                menuState.getStartForFree()
            )
            .build();
        
        // Verify we're in the menu by finding any menu element
        ActionResult result = ConditionalActionChain
            .findAny(menuElements)
            .perform(action, menuElements);
            
        if (result.isSuccess()) {
            log.info("Successfully confirmed Menu state is active");
            return true;
        } else {
            log.error("Failed to confirm Menu state - no menu elements found");
            return false;
        }
    }
}