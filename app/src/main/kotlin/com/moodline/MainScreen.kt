package com.moodline

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.TagFaces
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.components.MoodlineBottomNavBar
import com.designsystem.components.MoodlineBottomNavBarItem
import com.designsystem.theme.MoodlineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MainScreen(
    mainUIState: MainUIState,
    onBottomNavItemClicked: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            MoodlineBottomNavBar(
                items = mainUIState.bottomNavBarItems.toBottomNavBarItems(),
                selectedItemId = mainUIState.selectedNavItemId.toString(),
                onItemClicked = onBottomNavItemClicked,
            )
        },
        content = content,
    )
}

@Composable
private fun ImmutableList<BottomNavBarId>.toBottomNavBarItems(): ImmutableList<MoodlineBottomNavBarItem> {
    return map {
        when (it) {
            BottomNavBarId.DIARY -> MoodlineBottomNavBarItem(
                id = it.toString(),
                title = stringResource(id = R.string.bottom_nav_title_diary),
                icon = Icons.Filled.Book,
            )

            BottomNavBarId.STATS -> MoodlineBottomNavBarItem(
                id = it.toString(),
                title = stringResource(id = R.string.bottom_nav_title_stats),
                icon = Icons.Filled.BarChart,
            )

            BottomNavBarId.IMPROVE -> MoodlineBottomNavBarItem(
                id = it.toString(),
                title = stringResource(id = R.string.bottom_nav_title_improve),
                icon = Icons.Filled.TagFaces,
            )
        }
    }.toPersistentList()
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MoodlineTheme {
        MainScreen(
            mainUIState = MainUIState(),
            onBottomNavItemClicked = {},
        ) {
        }
    }
}