package com.example.koin_apps.presenter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.koin_apps.presenter.view.tickerList.TickerListPageTopBar
import com.example.koin_apps.presenter.viewModel.MainViewModel

@Composable
fun TickerListPage(
    modifier: Modifier,
    tickerListViewModel: MainViewModel = hiltViewModel()
) {
    val tickerList by tickerListViewModel.tickerList.collectAsState()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
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
                LazyColumn(content = {})
            }
        )
    }
}

@Preview(group = "Page", showBackground = true)
@Composable
fun PreviewTickerListPage() {
    TickerListPage(modifier = Modifier)
}