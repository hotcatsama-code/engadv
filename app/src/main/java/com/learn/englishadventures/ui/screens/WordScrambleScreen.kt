package com.learn.englishadventures.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun WordScrambleScreen(vm: WordScrambleViewModel) {
    val state by vm.state.collectAsState()
    val focusManager = LocalFocusManager.current
    val haptics = LocalHapticFeedback.current

    val bgColor by animateColorAsState(
        targetValue = when (state.correct) {
            true -> Color(0xFFB9F6CA) // light green
            false -> Color(0xFFFFCDD2) // light red
            null -> MaterialTheme.colorScheme.background
        }, label = "bg"
    )

    val shake = remember { Animatable(0f) }
    LaunchedEffect(state.correct) {
        if (state.correct == false) {
            haptics.performHapticFeedback(HapticFeedbackType.LongPress)
            shake.animateTo(
                targetValue = 1f,
                animationSpec = keyframes {
                    durationMillis = 400
                    0f at 0
                    -12f at 50
                    12f at 100
                    -8f at 150
                    8f at 200
                    -4f at 250
                    4f at 300
                    0f at 400
                }
            )
        } else if (state.correct == true) {
            haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
            // brief flash then move next
            delay(450)
            vm.next()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "کلمات درهم 🧩", style = MaterialTheme.typography.titleLarge)
        Text(text = "${state.index + 1} / ${state.total}")
        Box(modifier = Modifier.offset(x = shake.value.dp)) {
            Text(text = state.scrambled, style = MaterialTheme.typography.titleLarge)
        }
        TextField(
            value = state.input,
            onValueChange = { vm.onInputChange(it) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus(); vm.checkAnswer()
            }),
            modifier = Modifier.fillMaxWidth(0.9f)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Button(onClick = { vm.checkAnswer() }) { Text(text = "بررسی پاسخ ✅") }
            Button(onClick = { vm.next() }) { Text(text = "بعدی ▶️") }
        }
    }
}
