package com.moodline

sealed interface MainNavigationEvent {

    class Default: MainNavigationEvent

    class GoToDiary: MainNavigationEvent

    class GoToStats: MainNavigationEvent

    class GoToImprove: MainNavigationEvent
}