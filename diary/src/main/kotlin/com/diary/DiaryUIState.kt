package com.diary

import com.domain.entities.Emotion
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class DiaryUIState(
    val entries: ImmutableList<DiaryItem> = persistentListOf(),
)

data class DiaryItem(
    val emotion: Emotion,
    val entryText: String,
    val formattedDate: String,
)
