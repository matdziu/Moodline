package com.addentry

import com.domain.entities.Emotion

data class AddEntryUIState(
    val selectedEmotion: Emotion? = null,
    val diaryEntryText: String = "",
)