package com.commontest

import com.domain.entities.DiaryEntry
import com.domain.entities.Emotion
import java.time.LocalDateTime

object SingleDiaryEntryProvider {

    fun provide(): DiaryEntry {
        return DiaryEntry(
            id = "testId",
            emotion = Emotion.Good,
            entryText = "test entry text",
            createdAt = LocalDateTime.of(2023, 7, 11, 3, 10)
        )
    }
}