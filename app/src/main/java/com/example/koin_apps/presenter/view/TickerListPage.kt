package com.example.koin_apps.presenter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.domain.entity.db.TickerEntity
import com.example.koin_apps.presenter.navController.ChoiceCurrency
import com.example.koin_apps.presenter.view.tickerList.TickerListItem
import com.example.koin_apps.presenter.view.tickerList.TickerListPageTopBar

@Composable
fun TickerListPage(
    navController: NavController,
    tickerList: List<TickerEntity>,
    fetchTickerData: () -> Unit,
    onClickTicker: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = Unit, block = { fetchTickerData() })

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        topBar = {
            TickerListPageTopBar(
                modifier = modifier,
                onAddClick = { navController.navigate(ChoiceCurrency.route) }
            )
        },
        content = { paddingValues ->
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    items(tickerList.size) {
                        TickerListItem(
                            modifier = Modifier,
                            ticker = tickerList[it].ticker,
                            onClickTicker = { onClickTicker(tickerList[it].ticker) }
                        )
                        Spacer(modifier = Modifier.size(5.dp))
                    }
                }
            )
        }
    )
}