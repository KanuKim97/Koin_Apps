package com.example.koin_apps.presenter.navController

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.koin_apps.presenter.view.TickerChoicePage
import com.example.koin_apps.presenter.view.TickerInfoPage
import com.example.koin_apps.presenter.view.TickerListPage

@Composable
fun NavHostController(navHostController: NavHostController) {
    NavHost(
        navController = navHostController, 
        startDestination = Home.route,
        builder = { 
            composable(
                route = Home.route,
                content = {
                    TickerListPage(
                        navController = navHostController,
                        onClickTicker = { ticker -> navHostController.navigateToTickerInfo(ticker) }
                    )
                }
            )
            composable(
                route = ChoiceCurrency.route,
                content = { TickerChoicePage(navController = navHostController) }
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