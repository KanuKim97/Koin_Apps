package com.example.koin_apps.presenter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceBtn
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoicePageTopBar

@Composable
fun TickerChoicePage(modifier: Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize().padding(10.dp),
        topBar = { TickerChoicePageTopBar(modifier = modifier) }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = { TickerChoiceBtn(modifier = modifier, onChoiceComplete = { /* TODO */ }) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTickerChoicePage() {
    TickerChoicePage(Modifier)
}