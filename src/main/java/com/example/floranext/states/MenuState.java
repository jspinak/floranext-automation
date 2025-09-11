package com.example.floranext.states;

import io.github.jspinak.brobot.manageStates.StateImage;
import io.github.jspinak.brobot.manageStates.State;
import lombok.Getter;
import lombok.Slf4j;

@State(initial = true)
@Getter
@Slf4j
public class MenuState {
    
    private final StateImage floranextIcon;
    private final StateImage floristWebsites;
    private final StateImage floristWebsitesSelected;
    private final StateImage pointOfSale;
    private final StateImage pricing;
    private final StateImage pricingSelected;
    private final StateImage resources;
    private final StateImage startForFree;
    private final StateImage toggleMenu;
    private final StateImage weddingsAndEvents;
    
    public MenuState() {
        log.info("Initializing MenuState");
        
        floranextIcon = new StateImage.Builder()
            .addPatterns("menu/menu-floranext_icon")
            .setName("Floranext Icon")
            .build();
            
        floristWebsites = new StateImage.Builder()
            .addPatterns("menu/menu-florist_websites")
            .setName("Florist Websites")
            .build();
            
        floristWebsitesSelected = new StateImage.Builder()
            .addPatterns("menu/menu-florist_websites_selected")
            .setName("Florist Websites Selected")
            .build();
            
        pointOfSale = new StateImage.Builder()
            .addPatterns("menu/menu-point_of_sale")
            .setName("Point of Sale")
            .build();
            
        pricing = new StateImage.Builder()
            .addPatterns("menu/menu-pricing")
            .setName("Pricing")
            .build();
            
        pricingSelected = new StateImage.Builder()
            .addPatterns("menu/menu-pricing_selected")
            .setName("Pricing Selected")
            .build();
            
        resources = new StateImage.Builder()
            .addPatterns("menu/menu-resources")
            .setName("Resources")
            .build();
            
        startForFree = new StateImage.Builder()
            .addPatterns("menu/menu-start_for_free")
            .setName("Start For Free")
            .build();
            
        toggleMenu = new StateImage.Builder()
            .addPatterns("menu/menu-toggle_menu")
            .setName("Toggle Menu")
            .build();
            
        weddingsAndEvents = new StateImage.Builder()
            .addPatterns("menu/menu-weddings_and_events")
            .setName("Weddings and Events")
            .build();
    }
}