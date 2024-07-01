package com.addentry.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.addentry.AddEntryRoute

const val addEntryRoute = "addEntry"

fun NavController.navigateToAddEntry(
    navOptions: NavOptions? = null
) {
    this.navigate(addEntryRoute, navOptions)
}

fun NavGraphBuilder.addEntryRoute(onBackButtonPressed: () -> Unit) {
    composable(route = addEntryRoute) {
        AddEntryRoute(onBackButtonPressed = onBackButtonPressed)
    }
}