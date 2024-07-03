package com.moodline.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
        enterTransition = { fadeIn(animationSpec = tween(400)) },
        exitTransition = { fadeOut(animationSpec = tween(400)) },
    ) {
        diaryRoute(
            navigateToAddEntry = { navController.navigateToAddEntry(it) },
            onBackButtonPressed = { closeApp() }
        )
        statsRoute(
            onBackButtonPressed = { navigateToDiary() }
        )
        improveRoute(
            onBackButtonPressed = { navigateToDiary() }
        )
        addEntryRoute(
            onBackButtonPressed = { navController.navigateUp() }
        )
    }
}