package com.example.ewaste.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColors = darkColorScheme(
    primary = PrimaryGreen,   // Ganti dengan warna hijau utama
    secondary = SecondaryGreen // Ganti dengan warna hijau sekunder
)

private val LightColors = lightColorScheme(
    primary = PrimaryGreen,    // Ganti dengan warna hijau utama
    secondary = SecondaryGreen // Ganti dengan warna hijau sekunder
)

@Composable
fun EWasteTheme(content: @Composable () -> Unit) {
    val colors = if (isSystemInDarkTheme()) DarkColors else LightColors
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}
