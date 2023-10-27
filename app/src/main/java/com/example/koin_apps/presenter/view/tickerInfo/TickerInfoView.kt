package com.example.koin_apps.presenter.view.tickerInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TickerInfoView(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            OrderInfoRow(modifier = modifier)
        }
    }
}


@Preview(group = "Views", showBackground = true)
@Composable
fun PreviewViews() {
    TickerInfoView(modifier = Modifier)
}
