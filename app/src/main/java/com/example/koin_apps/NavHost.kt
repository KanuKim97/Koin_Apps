package com.example.koin_apps

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavHostController(navHostController: NavHostController) {
    NavHost(
        navController = navHostController, 
        startDestination = ROUTE_HOME,
        builder = {
        }
    ) 
}