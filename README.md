# Floranext Automation Application

A Brobot-based automation application for navigating the Floranext website.

## Project Structure

```
floranext/
├── src/main/java/com/example/floranext/
│   ├── states/
│   │   ├── MenuState.java        - Menu navigation state
│   │   ├── PricingState.java     - Pricing page state
│   │   └── HomepageState.java    - Homepage state
│   ├── transitions/
│   │   ├── MenuToPricingTransition.java    - Navigate from menu to pricing
│   │   └── MenuToHomepageTransition.java   - Navigate from menu to homepage
│   ├── runner/
│   │   └── FloranextAutomationRunner.java  - Main automation sequence
│   └── FloranextApplication.java           - Spring Boot main class
├── images/
│   ├── menu/        - Menu state images
│   ├── pricing/     - Pricing state images
│   └── homepage/    - Homepage state images
└── build.gradle     - Project dependencies
```

## Automation Sequence

The application performs the following steps:
1. Starts from the menu state (initial state)
2. Navigates to the pricing page by clicking the pricing menu item
3. Clicks the "Start for Free" button on the pricing page
4. Navigates to the homepage by clicking the Floranext icon
5. Clicks the "Enter Your Email" field on the homepage

## Prerequisites

- Java 21 or higher
- Gradle
- Floranext website should be open in your browser

## Building the Application

```bash
cd floranext
./gradlew build
```

## Running the Application

```bash
./gradlew bootRun
```

## Configuration

The application configuration is in `src/main/resources/application.yml`:
- `brobot.core.mock`: Set to `true` for testing without real UI
- `brobot.core.image-path`: Path to the images directory
- `brobot.find.similarity`: Pattern matching similarity threshold (0.8 = 80%)
- `brobot.logging.verbosity`: Logging level (VERBOSE, INFO, etc.)

## Testing in Mock Mode

To test without a real browser, set `brobot.core.mock: true` in application.yml.

## Troubleshooting

- If images are not found, adjust the `brobot.find.similarity` value (lower = less strict)
- Check that the browser is visible and not minimized
- Ensure the images match the current UI state of the website