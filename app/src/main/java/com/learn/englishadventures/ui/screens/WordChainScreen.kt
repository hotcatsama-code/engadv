package com.learn.englishadventures.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WordChainScreen(vm: WordChainViewModel) {
    val state by vm.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "زنجیره کلمات 🔗", style = MaterialTheme.typography.titleLarge)
        Text(text = state.currentWord, style = MaterialTheme.typography.titleLarge)
        Button(onClick = { vm.next() }) { Text(text = "بعدی ▶️") }
        Text(text = "${state.index + 1} / ${state.total}")
    }
}
