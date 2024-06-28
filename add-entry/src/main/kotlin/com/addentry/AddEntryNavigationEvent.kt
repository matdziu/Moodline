package com.addentry

sealed interface AddEntryNavigationEvent {

    data object Default : AddEntryNavigationEvent
}