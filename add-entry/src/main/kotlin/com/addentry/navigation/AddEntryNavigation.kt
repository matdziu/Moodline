package com.addentry.navigation

import android.content.Intent
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.addentry.AddEntryRoute
import com.common.constants.ADD_ENTRY_DEEP_LINK

const val entryIdArgument = "entryId"
const val addEntryRoutePrefix = "addEntry"
const val addEntryRoute = "${addEntryRoutePrefix}/{$entryIdArgument}"

fun NavController.navigateToAddEntry(
    entryId: String? = null,
    navOptions: NavOptions? = null
) {
    this.navigate("${addEntryRoutePrefix}/$entryId", navOptions)
}

fun NavGraphBuilder.addEntryRoute(onBackButtonPressed: () -> Unit) {
    composable(
        route = addEntryRoute,
        arguments = listOf(
            navArgument(entryIdArgument) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }),
        deepLinks = listOf(
            navDeepLink {
                uriPattern = ADD_ENTRY_DEEP_LINK
                action = Intent.ACTION_VIEW
            }
        )
    ) {
        AddEntryRoute(onBackButtonPressed = onBackButtonPressed)
    }
}