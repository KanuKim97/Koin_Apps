package com.example.koin_apps.presenter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.entity.api.ticker.TickerLiveDataEntity
import com.example.koin_apps.presenter.view.tickerInfo.TickerInfoTopRow
import com.example.koin_apps.presenter.view.tickerInfo.TickerInfoView
import com.example.koin_apps.presenter.viewModel.TickerInfoViewModel

@Composable
fun TickerInfoPage(
    ticker: String?,
    modifier: Modifier = Modifier,
    tickerInfoViewModel: TickerInfoViewModel = hiltViewModel()
) {
    LaunchedEffect(
        key1 = Unit,
        block = { tickerInfoViewModel.fetchTickerDetailInfo(ticker.toString()) }
    )
    val tickerData by tickerInfoViewModel.tickerData.collectAsState(
        initial = TickerLiveDataEntity(
            0,
            0,
            0.0
        )
    )

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
                    tickerPrice = "${tickerData.closingPrice}원",
                    tickerFluctate = "${tickerData.fluctate_Rate}%",
                    tickerFluctatePrice = "${tickerData.fluctate_24H}원"
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