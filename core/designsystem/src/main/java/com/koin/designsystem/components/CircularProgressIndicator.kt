package com.koin.designsystem.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun KoinCircularIndicator() {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.onPrimary,
        strokeWidth = 2.dp
    )
}