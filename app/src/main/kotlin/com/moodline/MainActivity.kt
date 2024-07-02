package com.moodline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.common.extensions.launchAndRepeatOnLifecycle
import com.designsystem.theme.MoodlineTheme
import com.diary.navigation.diaryRoute
import com.improve.navigation.improveRoute
import com.moodline.navigation.MoodlineNavHost
import com.stats.navigation.statsRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val navController = rememberNavController()
            val mainViewModel: MainViewModel = hiltViewModel()

            val state by mainViewModel.state.collectAsStateWithLifecycle()
            LocalLifecycleOwner.current.launchAndRepeatOnLifecycle {
                mainViewModel.navigationEvents.collect {
                    when (it) {

                        is MainNavigationEvent.GoToDiary -> navigateToBottomNavDestination(
                            route = diaryRoute,
                            navController = navController,
                        )

                        is MainNavigationEvent.GoToImprove -> navigateToBottomNavDestination(
                            route = improveRoute,
                            navController = navController,
                        )

                        is MainNavigationEvent.GoToStats -> navigateToBottomNavDestination(
                            route = statsRoute,
                            navController = navController,
                        )

                        is MainNavigationEvent.Default -> {
                            // do nothing
                        }
                    }
                }
            }

            MoodlineTheme {
                MainScreen(
                    mainUIState = state,
                    onBottomNavItemClicked = mainViewModel::onBottomNavItemClicked,
                ) { innerPadding ->
                    MoodlineNavHost(
                        navController = navController,
                        closeApp = ::finish,
                        navigateToDiary = {
                            mainViewModel.onBottomNavItemClicked(BottomNavBarId.DIARY.toString())
                        },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    private fun navigateToBottomNavDestination(
        route: String,
        navController: NavHostController,
    ) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}