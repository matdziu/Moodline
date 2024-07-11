package com.commontest

import com.domain.entities.DiaryEntry
import com.domain.entities.Emotion
import java.time.LocalDateTime

object DiaryEntriesProvider {

    fun provide(): List<DiaryEntry> {
        return listOf(
            DiaryEntry(
                id = "testId1",
                emotion = Emotion.Rad,
                entryText = "test text 1",
                createdAt = LocalDateTime.of(2023, 7, 11, 12, 0)
            ),
            DiaryEntry(
                id = "testId3",
                emotion = Emotion.Meh,
                entryText = "test text 3",
                createdAt = LocalDateTime.of(2023, 5, 20, 3, 20)
            ),
            DiaryEntry(
                id = "testId2",
                emotion = Emotion.Good,
                entryText = "test text 2",
                createdAt = LocalDateTime.of(2023, 6, 1, 13, 10)
            ),
        )
    }
}