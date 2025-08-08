package com.learn.englishadventures.navigation

sealed class Screen(val route: String) {
    data object MainMenu : Screen("main_menu")
    data object Settings : Screen("settings")
    data class GameScreen(val gameType: String) : Screen("game/{gameType}") {
        companion object {
            const val Key = "gameType"
            fun route(gameType: String) = "game/$gameType"
        }
    }
}
