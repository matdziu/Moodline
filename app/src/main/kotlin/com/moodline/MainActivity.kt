package com.moodline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.TagFaces
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.designsystem.components.MoodlineBottomNavBarItem
import com.designsystem.theme.MoodlineTheme
import com.diary.navigation.diaryRoute
import com.improve.navigation.improveRoute
import com.moodline.navigation.MoodlineNavHost
import com.stats.navigation.statsRoute
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.collections.immutable.persistentListOf

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val navigationBarItems by lazy {
        persistentListOf(
            MoodlineBottomNavBarItem(
                id = "",
                route = diaryRoute,
                title = getString(R.string.bottom_nav_title_diary),
                icon = Icons.Filled.Book,
            ),
            MoodlineBottomNavBarItem(
                id = "",
                route = statsRoute,
                title = getString(R.string.bottom_nav_title_stats),
                icon = Icons.Filled.BarChart,
            ),
            MoodlineBottomNavBarItem(
                id = "",
                route = improveRoute,
                title = getString(R.string.bottom_nav_title_improve),
                icon = Icons.Filled.TagFaces,
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {
            val navController = rememberNavController()
            val mainViewModel: MainViewModel = hiltViewModel()

            MoodlineTheme {
                MainScreen(
                    navigationBarItems = navigationBarItems,
                    selectedItemId = "1",
                    onItemClicked = {},
                ) { innerPadding ->
                    MoodlineNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}