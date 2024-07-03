package com.diary

sealed interface DiaryNavigationEvent {

    data object Default : DiaryNavigationEvent
}