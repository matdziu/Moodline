package com.addentry

import com.domain.entities.Emotion

sealed interface AddEntryUIEvent {

    data class EmotionSelected(val emotion: Emotion): AddEntryUIEvent

    data class DiaryEntryTextChanged(val newText: String): AddEntryUIEvent

    data object AddButtonPressed: AddEntryUIEvent

    data object CancelButtonPressed: AddEntryUIEvent
}