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
import com.example.koin_apps.presenter.view.tickerList.TickerListPageTopBar

@Composable
fun TickerListPage(modifier: Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TickerListPageTopBar(
                modifier = modifier,
                onAddClick = { /* TODO */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {

            }
        )
    }
}

@Preview(group = "Page", showBackground = true)
@Composable
fun PreviewTickerListPage() {
    TickerListPage(modifier = Modifier)
}