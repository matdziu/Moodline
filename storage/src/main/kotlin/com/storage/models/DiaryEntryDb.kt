package com.storage.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "diaryEntry")
internal data class DiaryEntryDb(
    @PrimaryKey val id: String,

    val emotion: EmotionDb,
    val entryText: String,
    val createdAt: LocalDateTime,
)