package com.learn.englishadventures.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learn.englishadventures.game_logic.LevelGenerator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WordScrambleViewModel : ViewModel() {
    data class UiState(
        val index: Int = 0,
        val total: Int = 100,
        val original: String = "",
        val scrambled: String = "",
        val input: String = "",
        val correct: Boolean? = null
    )

    private val levels = LevelGenerator.allocateForGames().first
    private val generated = LevelGenerator.generateScrambleLevelsFrom(levels)

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        load(0)
    }

    fun onInputChange(v: String) { _state.value = _state.value.copy(input = v.uppercase()) }

    fun checkAnswer() {
        val s = _state.value
        _state.value = s.copy(correct = s.input.trim().uppercase() == s.original)
    }

    fun next() {
        val next = (_state.value.index + 1).coerceAtMost(generated.lastIndex)
        load(next)
    }

    private fun load(i: Int) {
        val (orig, scr) = generated[i]
        _state.value = UiState(index = i, original = orig, scrambled = scr)
    }
}
