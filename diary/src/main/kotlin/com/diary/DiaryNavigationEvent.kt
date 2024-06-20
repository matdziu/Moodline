package com.diary

sealed interface DiaryNavigationEvent {

    data object Default : DiaryNavigationEvent

    data object GoToAddEntry : DiaryNavigationEvent
}