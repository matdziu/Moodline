package com.stats.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stats.StatsRoute

const val statsRoute = "stats"

fun NavGraphBuilder.statsRoute() {
    composable(route = statsRoute) {
        StatsRoute()
    }
}