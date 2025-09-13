package com.example.floranext.states;

import io.github.jspinak.brobot.model.state.StateImage;
import io.github.jspinak.brobot.annotations.State;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@State
@Getter
@Slf4j
public class PricingState {
    
    private final StateImage startForFree;
    
    public PricingState() {
        log.info("Initializing PricingState");
        
        startForFree = new StateImage.Builder()
            .addPatterns("pricing/pricing-start-for-free")
            .setName("Start For Free Button")
            .build();
    }
}