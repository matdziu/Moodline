package com.diary.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.diary.DiaryRoute

const val diaryRoute = "diary"

fun NavGraphBuilder.diaryRoute(
    navigateToAddEntry: () -> Unit,
) {
    composable(route = diaryRoute) {
        DiaryRoute(
            navigateToAddEntry = navigateToAddEntry,
        )
    }
}