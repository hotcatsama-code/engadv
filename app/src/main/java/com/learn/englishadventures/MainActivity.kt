package com.learn.englishadventures

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.lifecycle.viewmodel.compose.viewModel
import com.learn.englishadventures.navigation.AppNavigationGraph
import com.learn.englishadventures.ui.screens.SettingsViewModel
import com.learn.englishadventures.ui.theme.EnglishAdventuresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                val settingsVm: SettingsViewModel = viewModel()
                val dark = settingsVm.darkMode.collectAsState().value
                val dyn = settingsVm.dynamicColor.collectAsState().value
                val scale = settingsVm.textScale.collectAsState().value
                EnglishAdventuresTheme(darkTheme = dark, dynamicColor = dyn, textScale = scale) {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        AppNavigationGraph()
                    }
                }
            }
        }
    }
}
