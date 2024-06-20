package com.moodline.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.addentry.navigation.addEntryRoute
import com.addentry.navigation.navigateToAddEntry
import com.diary.navigation.diaryRoute
import com.improve.navigation.improveRoute
import com.stats.navigation.statsRoute

@Composable
fun MoodlineNavHost(
    navController: NavHostController,
    closeApp: () -> Unit,
    navigateToDiary: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = diaryRoute,
        modifier = modifier,
    ) {
        diaryRoute(
            navigateToAddEntry = { navController.navigateToAddEntry() },
            onBackButtonPressed = { closeApp() }
        )
        statsRoute(
            onBackButtonPressed = { navigateToDiary() }
        )
        improveRoute(
            onBackButtonPressed = { navigateToDiary() }
        )
        addEntryRoute()
    }
}