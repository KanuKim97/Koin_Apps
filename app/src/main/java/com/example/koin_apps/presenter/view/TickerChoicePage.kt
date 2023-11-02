package com.example.koin_apps.presenter.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceBottomBar
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceListItem
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceTopBar
import com.example.koin_apps.presenter.viewModel.ChoiceViewModel

@Composable
fun TickerChoicePage(
    modifier: Modifier,
    onChoiceComplete: () -> Unit,
    choiceViewModel: ChoiceViewModel = hiltViewModel()
) {
    val checkedItem = remember { mutableListOf<String>() }
    val tickerList by choiceViewModel.tickerList.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TickerChoiceTopBar(modifier = modifier) },
        bottomBar = {
            TickerChoiceBottomBar(
                modifier = modifier,
                onChoiceComplete = {

                }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                content = {
                    items(tickerList.size) {
                        TickerChoiceListItem(
                            modifier = modifier,
                            ticker = tickerList[it],
                            checkedItem = checkedItem
                        )
                        Spacer(modifier = modifier.size(10.dp))
                    }
                }
            )
        }
    )
}