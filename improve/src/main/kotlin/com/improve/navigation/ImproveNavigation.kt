package com.improve.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.improve.ImproveRoute

const val improveRoute = "improve"

fun NavGraphBuilder.improveRoute() {
    composable(route = improveRoute) {
        ImproveRoute()
    }
}