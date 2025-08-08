package com.learn.englishadventures.ui.screens

import androidx.lifecycle.ViewModel
import com.learn.englishadventures.game_logic.LevelGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LetterHopViewModel : ViewModel() {
    data class UiState(
        val index: Int = 0,
        val total: Int = 100,
        val target: String = "",
        val grid: List<List<Char>> = emptyList()
    )

    private val levels = LevelGenerator.allocateForGames().third
    private val generated = levels.map { LevelGenerator.generateLetterHopFor(it) }

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init { load(0) }

    fun next() { load((_state.value.index + 1).coerceAtMost(generated.lastIndex)) }

    private fun load(i: Int) {
        val lvl = generated[i]
        _state.value = UiState(index = i, target = lvl.target, grid = lvl.grid)
    }
}
