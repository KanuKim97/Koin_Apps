package com.example.koin_apps.presenter.view

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.koin_apps.presenter.navController.Home
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceBottomBar
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceListItem
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceTopBar
import com.example.koin_apps.presenter.viewModel.ChoiceViewModel

@Composable
fun TickerChoicePage(
    navController: NavController,
    modifier: Modifier = Modifier,
    choiceViewModel: ChoiceViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current
    val checkedItem = remember { mutableListOf<String>() }
    val tickerList by choiceViewModel.tickerList.collectAsState()
    val isSaveSuccess by choiceViewModel.isSaveSuccess.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TickerChoiceTopBar(modifier = modifier) },
        bottomBar = {
            TickerChoiceBottomBar(
                modifier = modifier,
                onChoiceComplete = {
                    choiceViewModel.storeTickerTitle(checkedItem)

                    when(isSaveSuccess) {
                        true -> { navController.navigate(Home.route) }
                        false -> {
                            Toast.makeText(
                                localContext,
                                "저장에 실패하였습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
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