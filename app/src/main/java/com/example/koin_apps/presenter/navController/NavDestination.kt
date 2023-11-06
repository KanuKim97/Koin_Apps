package com.example.koin_apps.presenter.navController

interface NavDestination { val route: String }

object Home: NavDestination { override val route: String = "Home" }

object ChoiceCurrency: NavDestination { override val route: String = "ChoiceCurrency" }

object TickerInfo: NavDestination { override val route: String = "TickerInfo" }