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
import com.example.koin_apps.presenter.view.tickerInfo.TickerInfoTopRow
import com.example.koin_apps.presenter.view.tickerInfo.TickerInfoView

@Composable
fun TickerInfoPage(
    ticker: String?,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        content = { paddingValues ->
            Column(
                modifier = modifier.padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TickerInfoTopRow(
                    ticker = "$ticker",
                    tickerPrice = "원",
                    tickerFluctate = "%",
                    tickerFluctatePrice = "원"
                )
                TickerInfoView(modifier = Modifier)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoPage() {
    TickerInfoPage(ticker = "")
}