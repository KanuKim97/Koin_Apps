package com.example.koin_apps.composeView.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val DarkModeColorPalette = darkColorScheme(
    primary = Black,
    primaryContainer = White,
    secondary = Red
)

private val LightModeColorPalette = lightColorScheme(
    primary = Mint100,
    primaryContainer = Blue100,
    secondary = Green100
)

@Composable
fun KoinApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val themes = if (darkTheme) { DarkModeColorPalette } else { LightModeColorPalette }

    MaterialTheme(
        colorScheme = themes,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}