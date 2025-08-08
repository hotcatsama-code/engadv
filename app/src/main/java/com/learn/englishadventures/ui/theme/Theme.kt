package com.learn.englishadventures.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle

private val LightColors = lightColorScheme(
    primary = md_theme_light_primary,
    onPrimary = md_theme_light_onPrimary
)

private val DarkColors = darkColorScheme(
    primary = md_theme_dark_primary,
    onPrimary = md_theme_dark_onPrimary
)

private fun scaledTypography(scale: Float) = Typography.copy(
    displayLarge = Typography.displayLarge?.copy(fontSize = (Typography.displayLarge?.fontSize ?: androidx.compose.ui.unit.TextUnit.Unspecified) * scale) ?: Typography.titleLarge,
    titleLarge = Typography.titleLarge.copy(fontSize = Typography.titleLarge.fontSize * scale),
    bodyLarge = Typography.bodyLarge.copy(fontSize = Typography.bodyLarge.fontSize * scale),
    labelLarge = Typography.labelLarge?.copy(fontSize = (Typography.labelLarge?.fontSize ?: androidx.compose.ui.unit.TextUnit.Unspecified) * scale) ?: Typography.bodyLarge
)

@Composable
fun EnglishAdventuresTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    textScale: Float = 1f,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val colors = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColors
        else -> LightColors
    }

    MaterialTheme(
        colorScheme = colors,
        typography = scaledTypography(textScale),
        content = content
    )
}
