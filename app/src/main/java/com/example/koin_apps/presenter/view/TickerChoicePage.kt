package com.example.koin_apps.presenter.view

import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.koin_apps.presenter.navController.Home
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceBottomBar
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceListItem
import com.example.koin_apps.presenter.view.tickerChoice.TickerChoiceTopBar

@Composable
fun TickerChoicePage(
    navController: NavController,
    tickerList: List<String>,
    isSaveState: Boolean,
    storeTickerTitle: (List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    val localContext = LocalContext.current
    val checkedItem = remember { mutableListOf<String>() }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TickerChoiceTopBar(modifier = modifier) },
        bottomBar = {
            TickerChoiceBottomBar(
                modifier = modifier,
                onChoiceComplete = {
                    storeTickerTitle(checkedItem)

                    when(isSaveState) {
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

@Preview
@Composable
fun Preview() {
    TickerChoicePage(
        navController = rememberNavController(),
        tickerList = listOf(),
        isSaveState = true,
        storeTickerTitle = {  }
    )
}