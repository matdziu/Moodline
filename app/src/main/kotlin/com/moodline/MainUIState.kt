package com.moodline

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

data class MainUIState(
    val selectedNavItemId: BottomNavBarId = BottomNavBarId.DIARY,
    val bottomNavBarItems: ImmutableList<BottomNavBarId> = BottomNavBarId.entries.toPersistentList(),
)

enum class BottomNavBarId {
    DIARY, STATS, IMPROVE
}