package com.example.floranext.transitions;

import com.example.floranext.states.MenuState;
import com.example.floranext.states.HomepageState;
import io.github.jspinak.brobot.actions.Action;
import io.github.jspinak.brobot.actions.ActionResult;
import io.github.jspinak.brobot.actions.actionConfig.ConditionalActionChain;
import io.github.jspinak.brobot.collection.StateImageCollection;
import io.github.jspinak.brobot.manageStates.Transition;
import lombok.RequiredArgsConstructor;
import lombok.Slf4j;

@Transition(from = MenuState.class, to = HomepageState.class)
@RequiredArgsConstructor
@Slf4j
public class MenuToHomepageTransition {
    
    private final MenuState menuState;
    private final HomepageState homepageState;
    private final Action action;
    
    public boolean execute() {
        log.info("Executing transition from Menu to Homepage");
        
        // Click on the Floranext icon to navigate away from Menu
        ActionResult clickResult = action.click(menuState.getFloranextIcon());
        
        if (clickResult.isSuccess()) {
            log.info("Successfully clicked Floranext icon");
        } else {
            log.error("Failed to click on Floranext icon");
        }
        
        return clickResult.isSuccess();
    }
}