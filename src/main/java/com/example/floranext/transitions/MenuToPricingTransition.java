package com.example.floranext.transitions;

import com.example.floranext.states.MenuState;
import com.example.floranext.states.PricingState;
import io.github.jspinak.brobot.actions.Action;
import io.github.jspinak.brobot.actions.ActionResult;
import io.github.jspinak.brobot.manageStates.Transition;
import lombok.RequiredArgsConstructor;
import lombok.Slf4j;

@Transition(from = MenuState.class, to = PricingState.class)
@RequiredArgsConstructor
@Slf4j
public class MenuToPricingTransition {
    
    private final MenuState menuState;
    private final PricingState pricingState;
    private final Action action;
    
    public boolean execute() {
        log.info("Executing transition from Menu to Pricing");
        
        // Click on the pricing menu item to navigate away from Menu
        ActionResult clickResult = action.click(menuState.getPricing());
        
        if (clickResult.isSuccess()) {
            log.info("Successfully clicked pricing menu item");
        } else {
            log.error("Failed to click on pricing menu item");
        }
        
        return clickResult.isSuccess();
    }
}