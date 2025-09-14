package com.example.floranext;

import com.example.floranext.states.HomepageState;
import com.example.floranext.states.PricingState;
import com.example.floranext.states.MenuState;
import com.example.floranext.transitions.PricingTransitions;
import io.github.jspinak.brobot.config.core.FrameworkSettings;
import io.github.jspinak.brobot.config.environment.ExecutionEnvironment;
import io.github.jspinak.brobot.action.Action;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test to verify the automation logic that was originally failing due to missing transitions.
 * This simulates what happens in the FloranextAutomationRunner.
 */
public class AutomationLogicTest {

    @Mock
    private Action mockAction;
    
    private HomepageState homepageState;
    private MenuState menuState;
    private PricingState pricingState;
    private PricingTransitions pricingTransitions;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        
        // Set up mock mode
        FrameworkSettings.mock = true;
        System.setProperty("brobot.mock.mode", "true");
        ExecutionEnvironment.setInstance(ExecutionEnvironment.builder()
            .mockMode(true)
            .build());
        
        FrameworkSettings.screenshotPath = "images";
        
        // Initialize states (these should work in mock mode now)
        homepageState = new HomepageState();
        menuState = new MenuState();
        pricingState = new PricingState();
        
        // Create transition with mock dependencies
        pricingTransitions = new PricingTransitions(menuState, pricingState, homepageState, mockAction);
    }

    @Test
    public void testStatesInitializeInMockMode() {
        System.out.println("=== Testing States Initialize in Mock Mode ===");
        
        // Verify states were created successfully
        assertNotNull(homepageState, "HomepageState should be created");
        assertNotNull(menuState, "MenuState should be created");
        assertNotNull(pricingState, "PricingState should be created");
        
        // Verify state images are not null (they should have null images in mock mode, but Pattern objects should exist)
        assertNotNull(homepageState.getStartForFreeBig(), "Homepage startForFreeBig should exist");
        assertNotNull(homepageState.getEnterYourEmail(), "Homepage enterYourEmail should exist");
        assertNotNull(menuState.getPricing(), "Menu pricing should exist");
        assertNotNull(pricingState.getStartForFree(), "Pricing startForFree should exist");
        
        System.out.println("✓ All states initialize successfully in mock mode");
    }

    @Test
    public void testPricingTransitionsInMockMode() {
        System.out.println("=== Testing Pricing Transitions in Mock Mode ===");
        
        // Test fromMenu transition (should return true in mock mode)
        boolean fromMenuResult = pricingTransitions.fromMenu();
        assertTrue(fromMenuResult, "fromMenu should return true in mock mode");
        
        // Test fromHomepage transition (should return true in mock mode)
        boolean fromHomepageResult = pricingTransitions.fromHomepage();
        assertTrue(fromHomepageResult, "fromHomepage should return true in mock mode");
        
        // Test verifyArrival (should return true in mock mode)
        boolean verifyArrivalResult = pricingTransitions.verifyArrival();
        assertTrue(verifyArrivalResult, "verifyArrival should return true in mock mode");
        
        System.out.println("✓ All pricing transitions work correctly in mock mode");
    }

    @Test 
    public void testTransitionAnnotationsArePresent() {
        System.out.println("=== Testing Transition Method Annotations ===");
        
        try {
            // Verify the transition methods have the proper annotations
            var fromMenuMethod = PricingTransitions.class.getMethod("fromMenu");
            assertTrue(fromMenuMethod.isAnnotationPresent(io.github.jspinak.brobot.annotations.FromTransition.class),
                      "fromMenu should have @FromTransition annotation");
            
            var fromHomepageMethod = PricingTransitions.class.getMethod("fromHomepage");
            assertTrue(fromHomepageMethod.isAnnotationPresent(io.github.jspinak.brobot.annotations.FromTransition.class),
                      "fromHomepage should have @FromTransition annotation");
            
            var verifyArrivalMethod = PricingTransitions.class.getMethod("verifyArrival");
            assertTrue(verifyArrivalMethod.isAnnotationPresent(io.github.jspinak.brobot.annotations.IncomingTransition.class),
                      "verifyArrival should have @IncomingTransition annotation");
                      
            System.out.println("✓ All transition methods have correct annotations");
            
        } catch (NoSuchMethodException e) {
            fail("Could not find transition methods: " + e.getMessage());
        }
    }

    @Test
    public void testSimulatedAutomationFlow() {
        System.out.println("=== Testing Simulated Automation Flow ===");
        
        // Simulate the automation flow that was originally failing:
        // 1. Navigate to pricing (fromMenu transition)
        // 2. Verify arrival at pricing 
        // 3. Click button on pricing page
        // 4. Navigate to homepage
        
        // Step 1: Simulate navigation to pricing via menu
        boolean navigationToPricing = pricingTransitions.fromMenu();
        assertTrue(navigationToPricing, "Should be able to navigate to pricing from menu");
        
        // Step 2: Verify arrival at pricing
        boolean pricingVerification = pricingTransitions.verifyArrival();
        assertTrue(pricingVerification, "Should verify arrival at pricing page");
        
        // Step 3: The automation would click a button here - we just verify the state image exists
        assertNotNull(pricingState.getStartForFree(), "Pricing button should be available");
        
        System.out.println("✓ Simulated automation flow completed successfully");
        System.out.println("This indicates that the original 'Path to state not found' error should be resolved");
    }
}