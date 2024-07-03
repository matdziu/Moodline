package com.diary

sealed interface DiaryUIEvent {

    data object Initialize : DiaryUIEvent

    data object Refresh : DiaryUIEvent
}