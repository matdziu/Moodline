package com.addentry

import com.domain.entities.Emotion
import java.time.LocalDate
import java.time.LocalTime

sealed interface AddEntryUIEvent {

    data class EmotionSelected(val emotion: Emotion) : AddEntryUIEvent

    data class DiaryEntryTextChanged(val newText: String) : AddEntryUIEvent

    data object SaveButtonPressed : AddEntryUIEvent

    data object CancelButtonPressed : AddEntryUIEvent

    data class TimeSelected(val localTime: LocalTime) : AddEntryUIEvent

    data class DateSelected(val localDate: LocalDate) : AddEntryUIEvent

    data object Initialize : AddEntryUIEvent

    data object FixErrorsToastDisplayed : AddEntryUIEvent
}