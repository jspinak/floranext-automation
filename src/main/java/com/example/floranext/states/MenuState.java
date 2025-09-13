package com.example.floranext.states;

import io.github.jspinak.brobot.model.state.StateImage;
import io.github.jspinak.brobot.annotations.State;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@State(initial = true, priority = 100)
@Getter
@Slf4j
public class MenuState {
    
    private final StateImage homepage;
    private final StateImage pricing;
    
    public MenuState() {
        log.info("Initializing MenuState with lower similarity thresholds for better matching");
        
        homepage = new StateImage.Builder()
            .addPatterns("menu/menu-homepage")
            .setName("Floranext Icon")
            .build();
            
        pricing = new StateImage.Builder()
            .addPatterns("menu/menu-pricing")
            .setName("Pricing tab")
            .build();
    }
}