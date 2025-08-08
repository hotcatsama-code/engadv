package com.learn.englishadventures.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import androidx.navigation.navArgument
import com.learn.englishadventures.ui.screens.MainMenuScreen
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import com.learn.englishadventures.ui.screens.WordScrambleScreen
import com.learn.englishadventures.ui.screens.WordScrambleViewModel
import com.learn.englishadventures.ui.screens.WordChainScreen
import com.learn.englishadventures.ui.screens.WordChainViewModel
import com.learn.englishadventures.ui.screens.LetterHopScreen
import com.learn.englishadventures.ui.screens.LetterHopViewModel
import com.learn.englishadventures.ui.screens.SettingsScreen
import com.learn.englishadventures.ui.screens.SettingsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavigationGraph() {
    val navController = rememberNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = Screen.MainMenu.route,
        enterTransition = { slideInHorizontally { it } + fadeIn() },
        exitTransition = { slideOutHorizontally { -it } + fadeOut() },
        popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
        popExitTransition = { slideOutHorizontally { it } + fadeOut() }
    ) {
        composable(Screen.MainMenu.route) {
            MainMenuScreen(
                onOpenSettings = { navController.navigate(Screen.Settings.route) },
                onOpenGame = { type -> navController.navigate(Screen.GameScreen.route(type)) }
            )
        }
        composable(Screen.Settings.route) {
            SettingsScreen(viewModel())
        }
        composable(
            route = Screen.GameScreen("").route,
            arguments = listOf(navArgument(Screen.GameScreen.Key) { type = NavType.StringType })
        ) { backStackEntry ->
            when (backStackEntry.arguments?.getString(Screen.GameScreen.Key)) {
                "scramble" -> WordScrambleScreen(viewModel())
                "chain" -> WordChainScreen(viewModel())
                "hop" -> LetterHopScreen(viewModel())
                else -> PlaceholderScreen("Unknown")
            }
        }
    }
}

@Composable
private fun PlaceholderScreen(title: String) {
    androidx.compose.material3.Text(text = title)
}
