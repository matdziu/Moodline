package com.improve.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.improve.ImproveRoute

const val improveRoute = "improve"

fun NavController.navigateToImprove(navOptions: NavOptions? = null) {
    this.navigate(improveRoute, navOptions)
}

fun NavGraphBuilder.improveRoute() {
    composable(route = improveRoute) {
        ImproveRoute()
    }
}