package com.example.floranext.states;

import io.github.jspinak.brobot.model.state.StateImage;
import io.github.jspinak.brobot.annotations.State;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@State
@Getter
@Slf4j
public class HomepageState {
    
    private final StateImage startForFreeBig;
    private final StateImage enterYourEmail;
    
    public HomepageState() {
        log.info("Initializing HomepageState");
        
        startForFreeBig = new StateImage.Builder()
            .addPatterns("homepage/start_for_free_big")
            .setName("Start For Free Big Button")
            .build();
            
        enterYourEmail = new StateImage.Builder()
            .addPatterns("homepage/enter_your_email")
            .setName("Enter Your Email Field")
            .build();
    }
}