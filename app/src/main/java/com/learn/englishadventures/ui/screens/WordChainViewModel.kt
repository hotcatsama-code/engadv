package com.learn.englishadventures.ui.screens

import androidx.lifecycle.ViewModel
import com.learn.englishadventures.game_logic.LevelGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class WordChainViewModel : ViewModel() {
    data class UiState(
        val index: Int = 0,
        val total: Int = 100,
        val currentWord: String = ""
    )

    private val levels = LevelGenerator.allocateForGames().second
    private val chain = levels

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init { load(0) }

    fun next() { load((_state.value.index + 1).coerceAtMost(chain.lastIndex)) }

    private fun load(i: Int) {
        _state.value = UiState(index = i, currentWord = chain[i])
    }
}
