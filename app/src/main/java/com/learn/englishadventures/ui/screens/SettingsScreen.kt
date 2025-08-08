package com.learn.englishadventures.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.Slider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.learn.englishadventures.R

@Composable
fun SettingsScreen(vm: SettingsViewModel) {
    val dark by vm.darkMode.collectAsState()
    val dyn by vm.dynamicColor.collectAsState()
    val anim by vm.animations.collectAsState()
    val hapt by vm.haptics.collectAsState()
    val scale by vm.textScale.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "تنظیمات ⚙️")
        SettingRow(label = "حالت شب 🌙", checked = dark, onChange = vm::setDark)
        SettingRow(label = "رنگ پویا 🎨", checked = dyn, onChange = vm::setDynamicColor)
        SettingRow(label = "انیمیشن‌ها ✨", checked = anim, onChange = vm::setAnimations)
        SettingRow(label = "لرزش/هپتیک 📳", checked = hapt, onChange = vm::setHaptics)
        Divider()
        Text(text = "اندازه متن 🅰️")
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "کوچک")
            Text(text = "بزرگ")
        }
        Slider(value = scale, onValueChange = vm::setTextScale, valueRange = 0.85f..1.3f)
    }
}

@Composable
private fun SettingRow(label: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = label)
        Switch(checked = checked, onCheckedChange = onChange)
    }
}
