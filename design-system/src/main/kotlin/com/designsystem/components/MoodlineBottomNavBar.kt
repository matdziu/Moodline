package com.designsystem.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.theme.MoodlineTheme
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MoodlineBottomNavBar(
    items: ImmutableList<MoodlineBottomNavBarItem>,
    selectedItemId: String,
    onItemClicked: (String) -> Unit,
) {
    NavigationBar {
        items.forEach {
            NavigationBarItem(
                selected = selectedItemId == it.id,
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(
                        text = it.title,
                        textAlign = TextAlign.Center,
                    )
                },
                onClick = {
                    onItemClicked(it.id)
                }
            )
        }
    }
}

data class MoodlineBottomNavBarItem(
    val id: String,
    val title: String,
    val icon: ImageVector,
)

@Preview
@Composable
private fun MoodlineBottomNavBarPreview() {
    MoodlineTheme {
        MoodlineBottomNavBar(
            items = persistentListOf(
                MoodlineBottomNavBarItem(
                    id = "1",
                    title = "First",
                    icon = Icons.Filled.Notifications,
                ),
                MoodlineBottomNavBarItem(
                    id = "2",
                    title = "Second",
                    icon = Icons.Filled.Call,
                ),
                MoodlineBottomNavBarItem(
                    id = "3",
                    title = "Third",
                    icon = Icons.Filled.Build,
                )
            ),
            selectedItemId = "1",
            onItemClicked = {},
        )
    }
}