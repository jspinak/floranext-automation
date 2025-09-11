package com.example.floranext.states;

import io.github.jspinak.brobot.manageStates.StateImage;
import io.github.jspinak.brobot.manageStates.State;
import lombok.Getter;
import lombok.Slf4j;

@State
@Getter
@Slf4j
public class PricingState {
    
    private final StateImage startForFree;
    
    public PricingState() {
        log.info("Initializing PricingState");
        
        startForFree = new StateImage.Builder()
            .addPatterns("pricing/pricing-start_for_free")
            .setName("Start For Free Button")
            .build();
    }
}