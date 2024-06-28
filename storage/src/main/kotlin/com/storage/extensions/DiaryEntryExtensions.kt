package com.storage.extensions

import com.domain.entities.DiaryEntry
import com.storage.models.DiaryEntryDb
import java.util.UUID

internal fun DiaryEntry.toDiaryEntryDb(): DiaryEntryDb {
    return DiaryEntryDb(
        id = UUID.randomUUID().toString(),
        emotion = emotion.toEmotionDb(),
        entryText = entryText,
        createdAt = createdAt,
    )
}

internal fun DiaryEntryDb.toDiaryEntry(): DiaryEntry {
    return DiaryEntry(
        emotion = emotion.toEmotion(),
        entryText = entryText,
        createdAt = createdAt,
    )
}