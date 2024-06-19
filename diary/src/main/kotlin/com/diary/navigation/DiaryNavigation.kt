package com.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.diary.DiaryRoute

const val diaryRoute = "diary"

fun NavController.navigateToDiary(navOptions: NavOptions? = null) {
    this.navigate(diaryRoute, navOptions)
}

fun NavGraphBuilder.diaryRoute() {
    composable(route = diaryRoute) {
        DiaryRoute()
    }
}