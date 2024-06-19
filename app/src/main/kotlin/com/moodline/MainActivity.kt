package com.moodline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.TagFaces
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.designsystem.components.MoodlineBottomNavBar
import com.designsystem.components.MoodlineBottomNavBarItem
import com.designsystem.theme.MoodlineTheme
import com.diary.navigation.diaryRoute
import com.improve.navigation.improveRoute
import com.moodline.navigation.MoodlineNavHost
import com.stats.navigation.statsRoute
import kotlinx.collections.immutable.persistentListOf

class MainActivity : ComponentActivity() {

    private val navigationBarItems by lazy {
        persistentListOf(
            MoodlineBottomNavBarItem(
                route = diaryRoute,
                title = getString(R.string.bottom_nav_title_diary),
                icon = Icons.Filled.Book,
            ),
            MoodlineBottomNavBarItem(
                route = statsRoute,
                title = getString(R.string.bottom_nav_title_stats),
                icon = Icons.Filled.BarChart,
            ),
            MoodlineBottomNavBarItem(
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
            MoodlineTheme {
                val navController = rememberNavController()
                Scaffold(
                    bottomBar = {
                        MoodlineBottomNavBar(
                            navController = navController,
                            items = navigationBarItems,
                        )
                    }
                ) { innerPadding ->
                    MoodlineNavHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}