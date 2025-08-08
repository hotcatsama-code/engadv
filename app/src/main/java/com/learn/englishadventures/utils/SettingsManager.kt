package com.learn.englishadventures.utils

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val STORE_NAME = "settings"
private val Context.dataStore by preferencesDataStore(name = STORE_NAME)

object SettingsManager {
    private val DARK_MODE = booleanPreferencesKey("dark_mode")
    private val DYNAMIC_COLOR = booleanPreferencesKey("dynamic_color")
    private val ANIMATIONS = booleanPreferencesKey("animations")
    private val HAPTICS = booleanPreferencesKey("haptics")
    private val TEXT_SCALE = floatPreferencesKey("text_scale")
    private val LAST_GAME = stringPreferencesKey("last_game")

    fun darkModeFlow(context: Context): Flow<Boolean> =
        context.dataStore.data.map { prefs: Preferences -> prefs[DARK_MODE] ?: false }

    fun dynamicColorFlow(context: Context): Flow<Boolean> =
        context.dataStore.data.map { it[DYNAMIC_COLOR] ?: true }

    fun animationsFlow(context: Context): Flow<Boolean> =
        context.dataStore.data.map { it[ANIMATIONS] ?: true }

    fun hapticsFlow(context: Context): Flow<Boolean> =
        context.dataStore.data.map { it[HAPTICS] ?: true }

    fun textScaleFlow(context: Context): Flow<Float> =
        context.dataStore.data.map { it[TEXT_SCALE] ?: 1f }

    fun lastGameFlow(context: Context): Flow<String?> =
        context.dataStore.data.map { it[LAST_GAME] }

    suspend fun setDarkMode(context: Context, enabled: Boolean) {
        context.dataStore.edit { it[DARK_MODE] = enabled }
    }
    suspend fun setDynamicColor(context: Context, enabled: Boolean) {
        context.dataStore.edit { it[DYNAMIC_COLOR] = enabled }
    }
    suspend fun setAnimations(context: Context, enabled: Boolean) {
        context.dataStore.edit { it[ANIMATIONS] = enabled }
    }
    suspend fun setHaptics(context: Context, enabled: Boolean) {
        context.dataStore.edit { it[HAPTICS] = enabled }
    }
    suspend fun setTextScale(context: Context, scale: Float) {
        context.dataStore.edit { it[TEXT_SCALE] = scale.coerceIn(0.85f, 1.3f) }
    }
    suspend fun setLastGame(context: Context, game: String) {
        context.dataStore.edit { it[LAST_GAME] = game }
    }
}
