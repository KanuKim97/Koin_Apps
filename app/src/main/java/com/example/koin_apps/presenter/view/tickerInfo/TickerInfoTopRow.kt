package com.example.koin_apps.presenter.view.tickerInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TickerInfoTopRow(
    modifier: Modifier,
    ticker: String,
    tickerPrice: String,
    tickerFluctate: String,
    tickerFluctatePrice: String
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        content = {
            Text(
                text = ticker,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = tickerPrice,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Text(
                        text = tickerFluctate,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = modifier.size(5.dp))
                    Text(
                        text = tickerFluctatePrice,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopRow() {
    TickerInfoTopRow(
        modifier = Modifier,
        ticker = "BTC",
        tickerPrice = "1,000,000원",
        tickerFluctate = "5.3%",
        tickerFluctatePrice = "20,000원"
    )
}