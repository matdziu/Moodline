package com.moodline

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.components.MoodlineBottomNavBar
import com.designsystem.components.MoodlineBottomNavBarItem
import com.designsystem.theme.MoodlineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MainScreen(
    navigationBarItems: ImmutableList<MoodlineBottomNavBarItem>,
    selectedItemId: String,
    onItemClicked: (String) -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            MoodlineBottomNavBar(
                items = navigationBarItems,
                selectedItemId = selectedItemId,
                onItemClicked = onItemClicked,
            )
        },
        content = content,
    )
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MoodlineTheme {
        MainScreen(
            navigationBarItems = persistentListOf(
                MoodlineBottomNavBarItem(
                    id = "1",
                    route = "first",
                    title = "First",
                    icon = Icons.Filled.Notifications,
                ),
                MoodlineBottomNavBarItem(
                    id = "2",
                    route = "second",
                    title = "Second",
                    icon = Icons.Filled.Call,
                ),
                MoodlineBottomNavBarItem(
                    id = "3",
                    route = "third",
                    title = "Third",
                    icon = Icons.Filled.Build,
                ),
            ),
            selectedItemId = "first",
            onItemClicked = {}) {
        }
    }
}