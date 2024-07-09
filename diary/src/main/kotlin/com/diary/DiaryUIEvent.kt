package com.diary

sealed interface DiaryUIEvent {

    data object Initialize : DiaryUIEvent

    data object OnDispose: DiaryUIEvent

    data class RemoveEntry(val diaryEntryId: String): DiaryUIEvent

    data class EditEntry(val diaryEntryId: String): DiaryUIEvent
}