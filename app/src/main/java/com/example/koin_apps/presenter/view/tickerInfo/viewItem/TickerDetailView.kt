package com.example.koin_apps.presenter.view.tickerInfo.viewItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TickerDetailView(
    modifier: Modifier,
    openingPrice: String,
    minPrice: String,
    maxPrice: String,
    prevClosingPrice: String,
    tradeUnits: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        content = {
            Text(text = "시가: $openingPrice", modifier = modifier.padding(3.dp))
            Text(text = "고가: $maxPrice", modifier = modifier.padding(3.dp), color = Color.Red)
            Text(text = "저가: $minPrice", modifier = modifier.padding(3.dp), color = Color.Blue)
            HorizontalDivider()
            Text(text = "전일 종가: $prevClosingPrice", modifier = modifier.padding(3.dp))
            Text(text = "거래량: $tradeUnits", modifier = modifier.padding(3.dp))
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailView() {
    TickerDetailView(Modifier, "10,000", "9,000", "12,000", "9,900", "181,289")
}