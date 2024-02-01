package com.koin.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koin.database.model.KoinTable
import com.koin.designsystem.components.KoinLazyColumn
import com.koin.designsystem.theme.KoinTheme
import com.koin.ui.component.TickerHomeItem
import com.koin.ui.preview.ComponentPreview

@Composable
internal fun HomeRoute(onTickerItemClick: (String) -> Unit) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val readAllTickerUiState by homeViewModel.readTickerState.collectAsStateWithLifecycle()

    HomeScreen(
        readTickerUiState = readAllTickerUiState,
        onTickerItemClick = onTickerItemClick
    )
}

@Composable
internal fun HomeScreen(
    readTickerUiState: ReadTickerUiState,
    onTickerItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (readTickerUiState) {
        is ReadTickerUiState.IsLoading -> { /* TODO */ }
        is ReadTickerUiState.IsSuccess -> {
            KoinLazyColumn(
                modifier = modifier.fillMaxSize(),
                content = {
                    items(
                        items = readTickerUiState.data,
                        itemContent = { ticker ->
                            TickerHomeItem(
                                ticker = ticker.Ticker,
                                modifier = modifier
                                    .wrapContentHeight()
                                    .fillMaxWidth(),
                                onCardClick = { onTickerItemClick(ticker.Ticker) }
                            )
                        }
                    )
                }
            )
        }
        is ReadTickerUiState.IsFailed -> { /* TODO */ }
    }
}

@ComponentPreview
@Composable
fun PreviewHomeScreen() {
    KoinTheme {
        HomeScreen(
            readTickerUiState = ReadTickerUiState.IsSuccess(data = listOf(KoinTable("BTC"), KoinTable("ETH"))),
            onTickerItemClick = {  }
        )
    }
}