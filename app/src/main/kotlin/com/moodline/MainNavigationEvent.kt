package com.moodline

sealed interface MainNavigationEvent {

    data object Default: MainNavigationEvent

    data object GoToDiary: MainNavigationEvent

    data object GoToStats: MainNavigationEvent

    data object GoToImprove: MainNavigationEvent
}