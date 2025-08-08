package com.learn.englishadventures.ui.screens

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.learn.englishadventures.utils.SettingsManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(app: Application) : AndroidViewModel(app) {
    private val ctx get() = getApplication<Application>()

    val darkMode: StateFlow<Boolean> = SettingsManager.darkModeFlow(ctx)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), false)
    val dynamicColor: StateFlow<Boolean> = SettingsManager.dynamicColorFlow(ctx)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), true)
    val animations: StateFlow<Boolean> = SettingsManager.animationsFlow(ctx)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), true)
    val haptics: StateFlow<Boolean> = SettingsManager.hapticsFlow(ctx)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), true)
    val textScale: StateFlow<Float> = SettingsManager.textScaleFlow(ctx)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 1f)

    fun setDark(enabled: Boolean) = viewModelScope.launch { SettingsManager.setDarkMode(ctx, enabled) }
    fun setDynamicColor(enabled: Boolean) = viewModelScope.launch { SettingsManager.setDynamicColor(ctx, enabled) }
    fun setAnimations(enabled: Boolean) = viewModelScope.launch { SettingsManager.setAnimations(ctx, enabled) }
    fun setHaptics(enabled: Boolean) = viewModelScope.launch { SettingsManager.setHaptics(ctx, enabled) }
    fun setTextScale(scale: Float) = viewModelScope.launch { SettingsManager.setTextScale(ctx, scale) }
}
