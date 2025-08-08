🟢 Phase 1: Project Foundation & Core Setup
🟢 Task 1.1: Create the Android Studio Project

Action: Create a new Android Studio project.

Name: EnglishAdventures

Package Name: com.learn.englishadventures

Language: Kotlin

Minimum SDK: API 28 (Android 9.0)

Build Configuration: Kotlin DSL (build.gradle.kts)

Template: Empty Activity (Jetpack Compose)

🟢 Task 1.2: Configure Gradle Dependencies

Action: Open the app/build.gradle.kts file and add the following dependencies using the latest stable versions:

Jetpack Compose (BOM, UI, Material3, Tooling)

Navigation for Compose (androidx.navigation:navigation-compose)

ViewModel for Compose (androidx.lifecycle:lifecycle-viewmodel-compose)

Room for database (Runtime, KTX for coroutines, Compiler ksp)

Kotlin Coroutines (kotlinx-coroutines-core, kotlinx-coroutines-android)

Preferences DataStore (androidx.datastore:datastore-preferences)

🟢 Task 1.3: Define Project Structure

Action: Create the following packages inside com.learn.englishadventures:

ui (with sub-packages: screens, components, theme)

data (with sub-packages: database, repository, models)

navigation

game_logic

utils

🟢 Task 1.4: Download and Integrate Shabnam Font

Action: This task makes a necessary exception to the "no external files" rule for UI beauty.

Find and download the regular and bold weights of the "Shabnam" font (.ttf files).

Create a new resource directory: res/font.

Place the downloaded .ttf files into the res/font directory. Rename them to be simple, e.g., shabnam_regular.ttf and shabnam_bold.ttf.

In the ui/theme package, create a new file named Font.kt.

In Font.kt, define a FontFamily that links to these font files.

In ui/theme/Type.kt, update the Typography object to use this new Shabnam FontFamily for all text styles.

🟢 Task 1.5: Setup App Theme and String Resources

Action: Go to the ui/theme package and ensure Theme.kt is configured for Material 3.

Action: Create the file res/values-fa/strings.xml for Persian UI text.

Clarification: These strings are for the User Interface ONLY (buttons, titles, settings). The game content itself will be in English.

Add strings: app_name, game_word_scramble, game_word_chain, game_letter_hop, settings, check_answer, dark_mode, etc.

Action: Copy these strings to res/values/strings.xml with English translations as a fallback.

Action: Open MainActivity.kt and wrap the entire app's composition in a CompositionLocalProvider to force LocalLayoutDirection provides LayoutDirection.Rtl.

🟢 Phase 2: Navigation & Responsive Core UI
🟢 Task 2.1: Define Navigation Routes

Action: In the navigation package, create AppRoutes.kt. Define a sealed class Screen with objects for MainMenu, Settings, and a class for GameScreen(gameType: String).

🟢 Task 2.2: Implement Navigation Host

Action: In navigation, create a Composable AppNavigationGraph. Set up an AnimatedNavHost with placeholder screens for each route to test navigation.

🟢 Task 2.3: Build a Responsive Main Menu Screen

Action: In ui/screens, create MainMenuScreen.kt.

Design the screen to be fully responsive:

Use a Column that fills the entire screen (modifier = Modifier.fillMaxSize()).

Use Arrangement.SpaceEvenly to ensure elements are spaced out nicely on any screen height.

Use Alignment.CenterHorizontally for horizontal alignment.

Create a reusable MenuButton composable in the ui/components package. The button's width should be defined using a fraction of the screen width (Modifier.fillMaxWidth(0.8f)).

Add three MenuButton instances for the games.

Add a "Settings" IconButton in the top-right corner using a Box with contentAlignment = Alignment.TopEnd.

🟢 Phase 3: Data Persistence & State
🟢 Task 3.1: Define Data Models & Database

Action: In data/models, create GameProgress.kt (data class GameProgress(val gameType: String, val lastCompletedLevel: Int)).

Action: In data/database, create ProgressEntity.kt (a Room @Entity), ProgressDao.kt (the @Dao), and AppDatabase.kt (the RoomDatabase).

🟢 Task 3.2: Create Repository & State Preservation

Action: In data/repository, create ProgressRepository.kt to manage data calls.

Action: When building ViewModels, use viewModelScope for coroutines and StateFlow to expose UI state. In the UI, use rememberSaveable for any transient state you want to preserve across configuration changes (like screen rotation).

🟢 Phase 4: Content & Game Logic Generation
🟢 Task 4.1: Create the English Word Bank

Action: In game_logic, create WordBank.kt.

Clarification: This object must contain lists of English words, categorized by difficulty (easyWords, mediumWords, hardWords). This content is for the games themselves.

🟢 Task 4.2: Create the Level Generation Logic

Action: In game_logic, create LevelGenerator.kt.

Word Scramble: Create generateScrambleLevels(count: Int). It must use English words from WordBank and ensure the scrambled version is not the same as the original.

Word Chain: Create generateChainLevels(count: Int). It will select starting English words.

Letter Hop: Create generateLetterHopLevels(count: Int). It must embed a target English word within a grid.

🟢 Phase 5-7: Building Responsive Games
General Task for all Games: For each game screen, create a ViewModel and a Composable Screen. The UI must be responsive.

🟢 Task 5: Build Word Scramble

Action: Create WordScrambleViewModel.kt and WordScrambleScreen.kt.

UI: The title "کلمات درهم" is in Persian. The scrambled word itself (e.g., "TCA") is in English. Use a TextField for input.

Responsiveness: Ensure the layout works in both portrait and landscape mode. The text field and buttons should not overflow. Use LazyColumn or Column(Modifier.verticalScroll(rememberScrollState())) if content might overflow on small screens.

Animation: Implement correct (green flash) and incorrect (red shake) animations.

🟢 Task 6: Build Word Chain

Action: Create WordChainViewModel.kt and WordChainScreen.kt.

UI: The title "زنجیره کلمات" is in Persian. The current word (e.g., "HOUSE") is in English.

Responsiveness: Same as above. The layout must adapt.

🟢 Task 7: Build Letter Hop

Action: Create LetterHopViewModel.kt and LetterHopScreen.kt.

UI: The title "پرش حروف" is in Persian. The target word (e.g., "KOTLIN") and the letter grid are in English.

Responsiveness: The grid of letters must be adaptive. Use LazyVerticalGrid with GridCells.Adaptive(minSize = 60.dp). This will automatically adjust the number of columns based on the screen width, ensuring it looks good on both phones and tablets.

🟢 Phase 8: Settings & Final Polish
🟢 Task 8.1: Implement Settings

Action: Create a SettingsManager.kt using Preferences DataStore.

Action: Build the SettingsScreen.kt. The text "حالت شب" should be in Persian. The Switch should dynamically update the app's theme (light/dark).

🟢 Task 8.2: Refine Animations

Action: Use AnimatedNavHost to add slide-in/slide-out and fade transitions between all screens.

🟢 Task 8.3: Accessibility

Action: Add contentDescription in Persian to all IconButtons and other non-text elements for screen readers.

Phase 9: Final Testing & Release

🟢 Task 9.1: Configure Release Build

Action: In app/build.gradle.kts, enable isMinifyEnabled = true and isShrinkResources = true for the release build type.

Task 9.2: Generate Release APK

Action: Generate a signed release APK using Android Studio's Build > Generate Signed Bundle / APK... wizard.

Task 9.4: Final Delivery

Action: The final deliverable is the app-release.apk file.

🟢 Phase 10: بهبودهای پیشرفته UX/UI و محصول

🟢 Task 10.1: تایپوگرافی و فونت

Action: نهایی‌سازی فونت شهبنام (Regular/Bold) و تنظیم سلسله‌مراتب تایپوگرافی (display/title/body/label) با lineHeight و letterSpacing مناسب فارسی. (این مورد در انتها توسط شما نهایی می‌شود)

🟢 Task 10.2: هویت بصری و Layout سیستماتیک

Action: تعریف Design Tokens (Spacing/Radius/Elevation) و استفاده از Card/Container و پس‌زمینه گرادیانی در صفحات.

🟢 Task 10.3: حلقه پیشرفت و انگیزش

Action: نمایش پیشرفت هر بازی (مرحله X از 100)، نشان‌ها پس از هر ۱۰ مرحله، و دکمه «ادامه» در منوی اصلی.

🟢 Task 10.4: تنظیم سختی و تعادل

Action: تنظیم منحنی سختی (easy/medium/hard) در 100 مرحله هر بازی و سازگاری اندازه شبکه Letter Hop با طول کلمه.

🟢 Task 10.5: بازخوردهای غنی و انیمیشن‌های خرد

Action: بازخورد لمسی (Haptic) برای درست/غلط، انیمیشن‌های خرد (shake/flash/confetti ساده) و Transition‌های ریز.

🟢 Task 10.6: دسترس‌پذیری

Action: تکمیل contentDescription فارسی، کنتراست مناسب، و قابلیت تغییر اندازه متن از داخل تنظیمات.

🟢 Task 10.7: تنظیمات بیشتر

Action: افزودن سوئیچ‌های فعال/غیرفعال انیمیشن‌ها و هپتیک، اسلایدر اندازه متن، و گزینه Dynamic Color.

🟢 Task 10.8: بهینه‌سازی کارایی

Action: بهینه‌سازی Recomposition با remember/rememberSaveable و پیش‌تولید مرحله بعدی.

� Task 10.9: آیکون و اسپلش داخلی

Action: ساخت آیکون برداری ساده و اسپلش ابتدایی درون اپ (بدون فایل خارجی).