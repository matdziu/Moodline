package com.diary

sealed interface DiaryNavigationEvent {

    class Default : DiaryNavigationEvent

    class GoToAddEntry : DiaryNavigationEvent
}