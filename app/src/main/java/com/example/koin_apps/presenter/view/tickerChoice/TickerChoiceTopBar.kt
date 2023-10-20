package com.example.koin_apps.presenter.view.tickerChoice

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickerChoicePageTopBar(modifier: Modifier) {
    TopAppBar(
        title = { Text(text = "암호화폐를 선택해주세요.") },
        modifier = modifier.wrapContentHeight()
    )
}