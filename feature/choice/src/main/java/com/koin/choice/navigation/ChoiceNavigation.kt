package com.koin.choice.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.koin.choice.ChoiceTickerRoute

const val ROUTE_CHOICE = "ChoiceRoute"

fun NavController.onNavigateChoice() {
    this.navigate(ROUTE_CHOICE)
}


fun NavGraphBuilder.choiceScreen(onNavigationBackClick: () -> Unit) {
    composable(
        route = ROUTE_CHOICE,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearEasing
                )
            ) + slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearEasing
                )
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearEasing
                )
            )
        },
        content = { ChoiceTickerRoute(onNavigationIconClick = onNavigationBackClick) }
    )
}