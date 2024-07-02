package com.addentry

sealed interface AddEntryNavigationEvent {

    data object Default : AddEntryNavigationEvent

    data object CloseScreen: AddEntryNavigationEvent
}