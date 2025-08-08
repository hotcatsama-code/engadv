package com.learn.englishadventures.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.learn.englishadventures.R
import com.learn.englishadventures.ui.components.MenuButton

@Composable
fun MainMenuScreen(
    onOpenSettings: () -> Unit,
    onOpenGame: (String) -> Unit
) {
    val gradient = Brush.verticalGradient(listOf(Color(0xFF0D47A1), Color(0xFF1976D2), Color(0xFF42A5F5)))

    Box(modifier = Modifier.fillMaxSize().background(gradient)) {
        IconButton(
            onClick = onOpenSettings,
            modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Settings, contentDescription = "تنظیمات")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "ماجراجویی‌های انگلیسی ✨", style = MaterialTheme.typography.titleLarge, color = Color.White)
            MenuButton(text = "کلمات درهم 🧩") { onOpenGame("scramble") }
            MenuButton(text = "زنجیره کلمات 🔗") { onOpenGame("chain") }
            MenuButton(text = "پرش حروف 🐸") { onOpenGame("hop") }
        }
    }
}
