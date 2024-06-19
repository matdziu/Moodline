package com.stats.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.stats.StatsRoute

const val statsRoute = "stats"

fun NavController.navigateToStats(navOptions: NavOptions? = null) {
    this.navigate(statsRoute, navOptions)
}

fun NavGraphBuilder.statsRoute() {
    composable(route = statsRoute) {
        StatsRoute()
    }
}