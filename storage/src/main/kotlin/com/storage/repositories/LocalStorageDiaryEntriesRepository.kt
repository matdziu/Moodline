package com.storage.repositories

import com.domain.entities.DiaryEntry
import com.domain.entities.Emotion
import com.domain.repositories.DiaryEntriesRepository
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import javax.inject.Inject

internal class LocalStorageDiaryEntriesRepository @Inject constructor() : DiaryEntriesRepository {

    override suspend fun getAll(): List<DiaryEntry> {
        delay(2000)
        return listOf(
            DiaryEntry(
                emotion = Emotion.Awful,
                entryText = "teafa111",
                createdAt = LocalDateTime.now()
            ),
            DiaryEntry(
                emotion = Emotion.Good,
                entryText = "teafa222",
                createdAt = LocalDateTime.now()
            )
        )
    }

    override suspend fun add(entry: DiaryEntry) {

    }
}