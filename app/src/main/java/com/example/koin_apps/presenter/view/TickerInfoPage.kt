package com.example.koin_apps.presenter.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koin_apps.presenter.view.tickerInfo.TickerInfoTopRow

@Composable
fun TickerInfoPage(modifier: Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        content = { paddingValues ->
            TickerInfoTopRow(
                modifier = modifier,
                ticker = "Koin",
                tickerPrice = "10000원",
                tickerFluctate = "2.1%",
                tickerFluctatePrice = "210원"
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoPage() {
    TickerInfoPage(modifier = Modifier)
}