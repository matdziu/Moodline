package com.domain.entities

import java.time.LocalDateTime

data class DiaryEntry(
    val id: String,
    val emotion: Emotion,
    val entryText: String,
    val createdAt: LocalDateTime,
)