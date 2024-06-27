package com.domain.entities

import java.time.LocalDateTime

data class DiaryEntry(
    val emotion: Emotion,
    val entryText: String,
    val createdAt: LocalDateTime,
)