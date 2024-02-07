package com.example.koin_apps

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.koin.choice.navigation.choiceScreen
import com.koin.choice.navigation.onNavigateChoice
import com.koin.home.navigation.ROUTE_HOME
import com.koin.home.navigation.homeScreen

@Composable
fun NavHostController(navHostController: NavHostController) {
    NavHost(
        navController = navHostController, 
        startDestination = ROUTE_HOME,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        homeScreen(
            onAddActionClick = { navHostController.onNavigateChoice() },
            onOptActionClick = {  }
        )
        choiceScreen(onNavigationBackClick = { navHostController.popBackStack() })
    }
}