package com.addentry

sealed interface AddEntryNavigationEvent {

    class Default : AddEntryNavigationEvent

    class CloseScreen: AddEntryNavigationEvent
}