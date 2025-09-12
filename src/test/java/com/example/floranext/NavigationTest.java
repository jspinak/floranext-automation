package com.example.floranext;

import com.example.floranext.states.HomepageState;
import com.example.floranext.states.PricingState;
import com.example.floranext.states.MenuState;
import io.github.jspinak.brobot.config.core.FrameworkSettings;
import io.github.jspinak.brobot.config.environment.ExecutionEnvironment;
import io.github.jspinak.brobot.navigation.transition.StateNavigator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test that verifies the navigation system can find paths between states
 * after fixing the @Component annotation issue.
 */
@SpringBootTest
@ActiveProfiles("mock")
public class NavigationTest {

    @Autowired
    private StateNavigator stateNavigator;
    
    @Autowired
    private HomepageState homepageState;
    
    @Autowired 
    private MenuState menuState;
    
    @Autowired
    private PricingState pricingState;

    @BeforeEach
    public void setupTest() {
        // Set up mock mode properly
        FrameworkSettings.mock = true;
        System.setProperty("brobot.mock.mode", "true");
        ExecutionEnvironment.setInstance(ExecutionEnvironment.builder()
            .mockMode(true)
            .build());
        
        FrameworkSettings.screenshotPath = "images";
    }

    @Test
    public void testStatesAreInjected() {
        System.out.println("=== Testing State Injection ===");
        
        assertNotNull(homepageState, "HomepageState should be injected");
        assertNotNull(menuState, "MenuState should be injected");
        assertNotNull(pricingState, "PricingState should be injected");
        assertNotNull(stateNavigator, "StateNavigator should be injected");
        
        System.out.println("✓ All states and navigator are properly injected");
    }

    @Test
    public void testNavigationToPricing() {
        System.out.println("=== Testing Navigation to Pricing State ===");
        
        // Test navigation to Pricing (this was failing before)
        boolean navigationResult = stateNavigator.openState("Pricing");
        
        if (navigationResult) {
            System.out.println("✓ Successfully navigated to Pricing state");
        } else {
            System.out.println("✗ Failed to navigate to Pricing state");
        }
        
        // In mock mode, this should succeed now that transitions are registered
        assertTrue(navigationResult, "Navigation to Pricing should succeed in mock mode");
    }

    @Test
    public void testNavigationToHomepage() {
        System.out.println("=== Testing Navigation to Homepage State ===");
        
        // Test navigation to Homepage 
        boolean navigationResult = stateNavigator.openState("Homepage");
        
        if (navigationResult) {
            System.out.println("✓ Successfully navigated to Homepage state");
        } else {
            System.out.println("✗ Failed to navigate to Homepage state");
        }
        
        assertTrue(navigationResult, "Navigation to Homepage should succeed in mock mode");
    }
    
    @Test
    public void testNavigationToMenu() {
        System.out.println("=== Testing Navigation to Menu State ===");
        
        // Test navigation to Menu
        boolean navigationResult = stateNavigator.openState("Menu");
        
        if (navigationResult) {
            System.out.println("✓ Successfully navigated to Menu state");
        } else {
            System.out.println("✗ Failed to navigate to Menu state");
        }
        
        assertTrue(navigationResult, "Navigation to Menu should succeed in mock mode");
    }
}