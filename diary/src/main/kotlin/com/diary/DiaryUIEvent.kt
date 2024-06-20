package com.diary

sealed interface DiaryUIEvent {

    data object AddEntryButtonPressed : DiaryUIEvent
}