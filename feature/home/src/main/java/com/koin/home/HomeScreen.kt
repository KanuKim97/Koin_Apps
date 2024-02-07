package com.koin.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koin.database.model.KoinTable
import com.koin.designsystem.components.KoinLazyColumn
import com.koin.designsystem.theme.KoinTheme
import com.koin.designsystem.theme.KoinTypography
import com.koin.ui.component.HomeTopAppBar
import com.koin.ui.component.TickerHomeItem
import com.koin.ui.preview.ComponentPreview

@Composable
internal fun HomeRoute(
    onAddActionClick: () -> Unit,
    onOptActionClick: () -> Unit,
    onTickerItemClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val homeViewModel = hiltViewModel<HomeViewModel>()
    val readAllTickerUiState by homeViewModel.readTickerState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HomeTopAppBar(
                title = { Text(text = "암호화폐 목록", style = KoinTypography.titleLarge) },
                onAddActionClick = onAddActionClick,
                onOptActionClick = onOptActionClick
            )
        }
    ) { paddingValues ->
        HomeScreen(
            readTickerUiState = readAllTickerUiState,
            onTickerItemClick = onTickerItemClick,
            paddingValues = paddingValues
        )
    }
}

@Composable
internal fun HomeScreen(
    readTickerUiState: ReadTickerUiState,
    onTickerItemClick: (String) -> Unit,
    paddingValues: PaddingValues,
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
                                    .fillMaxWidth()
                                    .padding(paddingValues),
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
            onTickerItemClick = {  },
            paddingValues = PaddingValues(10.dp)
        )
    }
}