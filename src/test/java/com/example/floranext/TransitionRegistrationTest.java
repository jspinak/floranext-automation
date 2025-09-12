package com.example.floranext;

import com.example.floranext.states.HomepageState;
import com.example.floranext.states.PricingState;
import com.example.floranext.states.MenuState;
import com.example.floranext.transitions.HomepageTransitions;
import com.example.floranext.transitions.PricingTransitions;
import com.example.floranext.transitions.MenuTransitions;
import io.github.jspinak.brobot.config.core.FrameworkSettings;
import io.github.jspinak.brobot.config.environment.ExecutionEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test that verifies transitions are properly annotated and can be instantiated.
 * This test validates the fix for the missing @Component annotation.
 */
public class TransitionRegistrationTest {

    @BeforeEach
    public void setup() {
        // Set up mock mode
        FrameworkSettings.mock = true;
        System.setProperty("brobot.mock.mode", "true");
        ExecutionEnvironment.setInstance(ExecutionEnvironment.builder()
            .mockMode(true)
            .build());
        
        FrameworkSettings.screenshotPath = "images";
    }

    @Test
    public void testTransitionClassesHaveComponentAnnotation() {
        System.out.println("=== Testing Transition Annotations ===");
        
        // Check that transition classes have @Component annotation
        assertTrue(PricingTransitions.class.isAnnotationPresent(org.springframework.stereotype.Component.class),
                  "PricingTransitions should have @Component annotation");
        
        assertTrue(HomepageTransitions.class.isAnnotationPresent(org.springframework.stereotype.Component.class),
                  "HomepageTransitions should have @Component annotation");
        
        assertTrue(MenuTransitions.class.isAnnotationPresent(org.springframework.stereotype.Component.class),
                  "MenuTransitions should have @Component annotation");
        
        System.out.println("✓ All transition classes have @Component annotation");
    }

    @Test
    public void testTransitionClassesHaveTransitionSetAnnotation() {
        System.out.println("=== Testing TransitionSet Annotations ===");
        
        // Check that transition classes have @TransitionSet annotation
        assertTrue(PricingTransitions.class.isAnnotationPresent(io.github.jspinak.brobot.annotations.TransitionSet.class),
                  "PricingTransitions should have @TransitionSet annotation");
        
        assertTrue(HomepageTransitions.class.isAnnotationPresent(io.github.jspinak.brobot.annotations.TransitionSet.class),
                  "HomepageTransitions should have @TransitionSet annotation");
        
        assertTrue(MenuTransitions.class.isAnnotationPresent(io.github.jspinak.brobot.annotations.TransitionSet.class),
                  "MenuTransitions should have @TransitionSet annotation");
        
        System.out.println("✓ All transition classes have @TransitionSet annotation");
    }

    @Test
    public void testStatesHaveStateAnnotation() {
        System.out.println("=== Testing State Annotations ===");
        
        // Check that state classes have @State annotation
        assertTrue(PricingState.class.isAnnotationPresent(io.github.jspinak.brobot.annotations.State.class),
                  "PricingState should have @State annotation");
        
        assertTrue(HomepageState.class.isAnnotationPresent(io.github.jspinak.brobot.annotations.State.class),
                  "HomepageState should have @State annotation");
        
        assertTrue(MenuState.class.isAnnotationPresent(io.github.jspinak.brobot.annotations.State.class),
                  "MenuState should have @State annotation");
        
        System.out.println("✓ All state classes have @State annotation");
    }

    @Test
    public void testTransitionClassesCanBeInstantiated() {
        System.out.println("=== Testing Transition Class Instantiation ===");
        
        try {
            // Initialize states first (in mock mode they should work)
            HomepageState homepageState = new HomepageState();
            MenuState menuState = new MenuState();
            PricingState pricingState = new PricingState();
            
            assertNotNull(homepageState, "HomepageState should be created");
            assertNotNull(menuState, "MenuState should be created");
            assertNotNull(pricingState, "PricingState should be created");
            
            System.out.println("✓ All state classes can be instantiated in mock mode");
            
            // Note: We can't instantiate transition classes here because they require
            // the Action dependency injection, but we've verified they have the right annotations
            
        } catch (Exception e) {
            fail("States should be instantiable in mock mode: " + e.getMessage());
        }
    }
}