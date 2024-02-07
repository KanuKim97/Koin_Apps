package com.koin.choice

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.koin.designsystem.components.KoinLazyColumn
import com.koin.ui.component.ChoiceTopAppBar
import com.koin.ui.component.TickerChoiceItem

@Composable
fun ChoiceTickerRoute(
    onNavigationIconClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val choiceViewModel = hiltViewModel<ChoiceViewModel>()
    val tickerAllUiState by choiceViewModel.tickerAllUiState.collectAsStateWithLifecycle()
    val insertTickerUiState by choiceViewModel.insertSuccessUiState.collectAsStateWithLifecycle()

    LaunchedEffect(
        key1 = Unit,
        block = { choiceViewModel.getTickerAll("KRW") }
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            ChoiceTopAppBar(
                title = { /*TODO*/ },
                onNavigationIconClick = onNavigationIconClick
            )
        }
    ) { paddingValues ->
        ChoiceTickerScreen(
            tickerAllUiState = tickerAllUiState,
            insertTickerUiState = insertTickerUiState,
            paddingValues = paddingValues
        )
    }
}

@Composable
fun ChoiceTickerScreen(
    tickerAllUiState: TickerAllUiState,
    insertTickerUiState: InsertTickerUiState,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    val checkedItemList = remember { mutableListOf<String>() }

    when (tickerAllUiState) {
        is TickerAllUiState.IsLoading -> {  }
        is TickerAllUiState.IsSuccess -> {
            KoinLazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(10.dp),
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