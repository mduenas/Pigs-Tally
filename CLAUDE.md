# Pigs Tally - Project Guide for Claude

## Project Overview

**Pigs Tally** is a Kotlin Multiplatform (KMP) mobile application that implements a digital scorekeeping system for the dice game "Pass the Pigs". The app targets both Android and iOS platforms using Compose Multiplatform for shared UI and business logic.

### Technology Stack

- **Primary Language**: Kotlin
- **UI Framework**: Compose Multiplatform
- **Architecture**: Kotlin Multiplatform (KMP)
- **Build System**: Gradle with Kotlin DSL
- **Platforms**: Android (API 24+) and iOS
- **State Management**: Kotlin Flows with ViewModels
- **Testing**: Kotlin Test framework

### Application Purpose

The app is a digital implementation of the "Pass the Pigs" dice game, featuring:
- Multi-player score tracking
- Game rule enforcement
- Turn-based gameplay management
- Settings and preferences
- Cross-platform compatibility

## Project Structure

```
/Users/markduenas/development/code/Pigs-Tally/
├── composeApp/                    # Main application module
│   ├── build.gradle.kts          # App-level build configuration
│   └── src/
│       ├── androidMain/          # Android-specific code
│       │   └── kotlin/com/markduenas/pigstally2/
│       │       └── MainActivity.kt
│       └── iosMain/              # iOS-specific code
│           └── kotlin/com/markduenas/pigstally2/
│               └── MainViewController.kt
├── shared/                       # Shared business logic and UI
│   ├── build.gradle.kts         # Shared module build configuration
│   └── src/
│       ├── commonMain/          # Cross-platform shared code
│       │   └── kotlin/com/markduenas/pigstally2/
│       │       ├── App.kt       # Main app entry point
│       │       ├── game/        # Game logic
│       │       │   ├── GameEngine.kt
│       │       │   └── GameViewModel.kt
│       │       ├── model/       # Data models
│       │       │   ├── GameState.kt
│       │       │   ├── Player.kt
│       │       │   ├── GameAction.kt
│       │       │   └── ScoringPosition.kt
│       │       ├── storage/     # Data persistence
│       │       │   ├── GameStateStorage.kt
│       │       │   └── PreferencesStorage.kt
│       │       └── ui/          # UI components
│       │           ├── components/
│       │           ├── screens/
│       │           └── theme/
│       ├── commonTest/          # Shared tests
│       │   └── kotlin/com/markduenas/pigstally2/
│       │       └── game/
│       │           └── GameEngineTest.kt
│       ├── androidMain/         # Android-specific implementations
│       │   └── kotlin/com/markduenas/pigstally2/storage/
│       └── iosMain/             # iOS-specific implementations
│           └── kotlin/com/markduenas/pigstally2/storage/
├── iosApp/                      # iOS native app wrapper
│   ├── iosApp.xcodeproj/       # Xcode project
│   └── iosApp/
│       ├── ContentView.swift   # SwiftUI integration
│       └── iOSApp.swift        # iOS app entry point
├── gradle/                      # Gradle configuration
│   └── libs.versions.toml      # Version catalog
├── build.gradle.kts            # Root build configuration
├── settings.gradle.kts         # Project settings
└── README.md                   # Basic project documentation
```

## Key Architecture Patterns

### 1. **Expect/Actual Pattern**
- Used for platform-specific implementations (storage, platform features)
- Common interfaces in `commonMain`, platform implementations in `androidMain`/`iosMain`

### 2. **MVVM with Compose**
- `GameViewModel` manages state using Kotlin Flows
- UI components observe state changes reactively
- Unidirectional data flow with actions

### 3. **Game Engine Pattern**
- `GameEngine` class handles all game logic and state transitions
- Pure functions for game state manipulation
- Comprehensive rule enforcement

### 4. **Repository Pattern**
- `GameStateStorage` and `PreferencesStorage` abstract data persistence
- Platform-specific implementations for Android (SharedPreferences) and iOS (UserDefaults)

## Build and Development Commands

### Prerequisites
- Android SDK (API 24+) configured with `ANDROID_HOME` environment variable
- Xcode (for iOS development)
- JDK 11 or higher

### Common Gradle Tasks

```bash
# Build the entire project
./gradlew build

# Assemble all variants
./gradlew assemble

# Run tests (requires Android SDK setup)
./gradlew test
./gradlew :shared:test

# Clean build artifacts
./gradlew clean

# Android-specific tasks
./gradlew :composeApp:assembleDebug
./gradlew :composeApp:assembleRelease
./gradlew :composeApp:installDebug

# iOS framework generation
./gradlew :shared:linkDebugFrameworkIosArm64
./gradlew :shared:linkReleaseFrameworkIosArm64
```

### Development Workflow

1. **Android Development**: Open project in Android Studio
2. **iOS Development**: 
   - Build shared framework: `./gradlew :shared:linkDebugFrameworkIosArm64`
   - Open `iosApp/iosApp.xcodeproj` in Xcode
3. **Shared Code**: Modify files in `shared/src/commonMain/`
4. **Platform-Specific**: Modify files in respective `androidMain`/`iosMain` directories

## Game Logic Overview

### Core Game Components

1. **GameState**: Central state container
   - Player list with scores
   - Current turn information
   - Game status (started, ended, winner)

2. **GameEngine**: Pure state transformation functions
   - Processes all game actions
   - Enforces game rules
   - Handles scoring calculations

3. **ScoringPosition**: Enum representing pig positions
   - TROTTER (5 points)
   - RAZORBACK (5 points)
   - SNOUTER (10 points)
   - DOT_UP/DOT_DOWN (sides - 1 point for matching pair)
   - OINKER (penalty - reset total score)
   - PIGGYBACK (penalty - eliminate player)

### Game Rules Implementation

- **Normal Scoring**: Two pigs with different non-side positions
- **Double Bonus**: Matching positions get base score + 10 bonus
- **Sider Scoring**: Both pigs on same side = 1 point
- **Pig Out**: Pigs on opposite sides = lose turn, no points
- **Immediate Penalties**: OINKER and PIGGYBACK end turn immediately

## Testing Strategy

### Test Coverage
- **Unit Tests**: `GameEngineTest.kt` covers all game logic scenarios
- **Test Scenarios**: Normal scoring, bonuses, penalties, win conditions
- **Platform Tests**: Currently focused on shared logic

### Running Tests
```bash
# Run all tests (requires Android SDK)
./gradlew test

# Run specific test class
./gradlew :shared:testDebugUnitTest --tests "GameEngineTest"
```

## UI Architecture

### Screen Navigation
- **App.kt**: Main navigation coordinator
- **GameSetupScreen**: Player management and game configuration
- **GameScreen**: Active gameplay interface
- **SettingsScreen**: User preferences

### State Management
- Reactive UI with `collectAsState()`
- Unidirectional data flow
- Actions dispatched to GameEngine via ViewModel

### Theming
- Custom color scheme in `ui/theme/Colors.kt`
- Consistent Material3 design system
- Platform-appropriate styling

## Development Guidelines

### Code Organization
- **Shared Logic**: Keep business logic in `commonMain`
- **Platform Specific**: Only use `androidMain`/`iosMain` for platform APIs
- **UI Components**: Compose components in `ui/components/`
- **Models**: Data classes in `model/` package

### Naming Conventions
- **Classes**: PascalCase (`GameEngine`, `GameState`)
- **Functions**: camelCase (`processAction`, `addPlayer`)
- **Constants**: UPPER_SNAKE_CASE (`WINNING_SCORE`)
- **Packages**: lowercase with dots (`com.markduenas.pigstally2.game`)

### State Management Rules
- **Immutable State**: Always copy state objects for updates
- **Pure Functions**: GameEngine methods should be side-effect free
- **Flow Usage**: Use StateFlow/SharedFlow for reactive state

## Common Issues and Solutions

### Build Issues
1. **Android SDK Not Found**: Set `ANDROID_HOME` environment variable
2. **iOS Build Fails**: Ensure Xcode is installed and updated
3. **Dependency Conflicts**: Clear Gradle cache with `./gradlew clean`

### Development Tips
1. **Hot Reload**: Use Android Studio's Compose preview for UI development
2. **Debugging**: Use println() statements in shared code for cross-platform debugging
3. **Testing**: Write tests in `commonTest` for shared logic validation

## Configuration Files

### Version Management
- `gradle/libs.versions.toml`: Centralized version catalog
- Key versions: Kotlin 2.1.21, Compose Multiplatform 1.8.1, Android compileSdk 35

### Build Configuration
- `build.gradle.kts`: Root project configuration
- `shared/build.gradle.kts`: Shared module with KMP setup
- `composeApp/build.gradle.kts`: Application module configuration

## Future Enhancement Areas

1. **AdMob Integration**: Documentation available in `AdMob_Integration_Guide.md`
2. **Data Persistence**: Enhanced game history and statistics
3. **Multiplayer**: Network-based multiplayer support
4. **Animations**: Enhanced UI transitions and game feedback
5. **Accessibility**: Improved screen reader support

## Resources

- [Kotlin Multiplatform Documentation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Pass the Pigs Rules](https://en.wikipedia.org/wiki/Pass_the_Pigs)

---

*This guide is maintained to help Claude instances understand and work effectively with the Pigs Tally codebase. Update this document when significant architectural changes are made.*