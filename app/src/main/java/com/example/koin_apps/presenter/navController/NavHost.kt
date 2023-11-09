package com.example.koin_apps.presenter.navController

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.koin_apps.presenter.view.TickerChoicePage
import com.example.koin_apps.presenter.view.TickerInfoPage
import com.example.koin_apps.presenter.view.TickerListPage
import com.example.koin_apps.presenter.viewModel.ChoiceViewModel
import com.example.koin_apps.presenter.viewModel.HomeViewModel

@Composable
fun NavHostController(navHostController: NavHostController) {
    NavHost(
        navController = navHostController, 
        startDestination = Home.route,
        builder = { 
            composable(
                route = Home.route,
                content = {
                    val homeViewModel = hiltViewModel<HomeViewModel>()
                    val tickerList by homeViewModel.tickerList.collectAsState()

                    TickerListPage(
                        navController = navHostController,
                        tickerList = tickerList,
                        fetchTickerData = homeViewModel::fetchAllTickerData,
                        onClickTicker = { ticker -> navHostController.navigateToTickerInfo(ticker) }
                    )
                }
            )
            composable(
                route = ChoiceCurrency.route,
                content = {
                    val choiceViewModel = hiltViewModel<ChoiceViewModel>()
                    val tickerList by choiceViewModel.tickerList.collectAsState()
                    val isSaveState by choiceViewModel.isSaveSuccess.collectAsState()

                    TickerChoicePage(
                        navController = navHostController,
                        tickerList = tickerList,
                        isSaveState = isSaveState,
                        storeTickerTitle = choiceViewModel::storeTickerTitle
                    )
                }
            )
            composable(
                route = TickerInfo.routeWithTicker,
                arguments = TickerInfo.argument,
                content = { navBackStackEntry ->
                    val ticker = navBackStackEntry.arguments?.getString(TickerInfo.ticker)
                    TickerInfoPage(ticker = ticker)
                }
            )
        }
    ) 
}

private fun NavHostController.navigateToTickerInfo(ticker: String) {
    this.navigate("${TickerInfo.route}/$ticker")
}