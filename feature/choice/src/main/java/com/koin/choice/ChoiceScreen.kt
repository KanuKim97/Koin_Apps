package com.koin.choice

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koin.designsystem.components.KoinLazyColumn
import com.koin.ui.component.TickerChoiceItem

@Composable
fun ChoiceTickerRoute() {
    val choiceViewModel = hiltViewModel<ChoiceViewModel>()
    val tickerAllUiState by choiceViewModel.tickerAllUiState.collectAsStateWithLifecycle()
    val insertTickerUiState by choiceViewModel.insertSuccessUiState.collectAsStateWithLifecycle()

    LaunchedEffect(
        key1 = Unit,
        block = { choiceViewModel.getTickerAll("KRW") }
    )

    ChoiceTickerScreen(
        tickerAllUiState = tickerAllUiState,
        insertTickerUiState = insertTickerUiState
    )
}

@Composable
fun ChoiceTickerScreen(
    tickerAllUiState: TickerAllUiState,
    insertTickerUiState: InsertTickerUiState,
    modifier: Modifier = Modifier
) {
    val checkedItemList = remember { mutableListOf<String>() }

    when (tickerAllUiState) {
        is TickerAllUiState.IsLoading -> {  }
        is TickerAllUiState.IsSuccess -> {
            KoinLazyColumn(
                modifier = modifier.fillMaxSize(),
                content = {
                    tickerAllUiState.data.data?.keys?.toList()?.let { tickerList ->
                        items(
                            items = tickerList,
                            itemContent = { ticker ->
                                TickerChoiceItem(
                                    ticker = ticker,
                                    checkedItemList = checkedItemList,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                )
                            }
                        )
                    }
                }
            )
        }
        is TickerAllUiState.IsFailed -> {  }
    }
}