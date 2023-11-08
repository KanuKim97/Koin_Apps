package com.example.koin_apps.presenter.navController

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface NavDestination { val route: String }

object Home: NavDestination { override val route: String = "Home" }

object ChoiceCurrency: NavDestination { override val route: String = "ChoiceCurrency" }

object TickerInfo: NavDestination {
    override val route: String = "TickerInfo"
    const val ticker = "ticker"
    val routeWithTicker: String = "$route/{$ticker}"
    val argument: List<NamedNavArgument> =
        listOf(navArgument(name = ticker, builder = { type = NavType.StringType }))
}