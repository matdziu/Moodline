package com.moodline.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.diary.navigation.diaryRoute
import com.improve.navigation.improveRoute
import com.stats.navigation.statsRoute

@Composable
fun MoodlineNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = diaryRoute,
        modifier = modifier,
    ) {
        diaryRoute()
        statsRoute()
        improveRoute()
    }
}