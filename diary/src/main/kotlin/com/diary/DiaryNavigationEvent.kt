package com.diary

sealed interface DiaryNavigationEvent {

    data object Default : DiaryNavigationEvent

    data class EditEntry(val entryId: String) : DiaryNavigationEvent
}