package com.addentry

import com.domain.entities.Emotion
import java.time.LocalDate
import java.time.LocalTime

data class AddEntryUIState(
    val selectedEmotion: Emotion? = null,
    val diaryEntryText: String = "",
    val progress: Boolean = false,
    val emotionNotSelectedError: Boolean = false,
    val selectedTime: LocalTime = LocalTime.now(),
    val selectedDate: LocalDate = LocalDate.now()
)