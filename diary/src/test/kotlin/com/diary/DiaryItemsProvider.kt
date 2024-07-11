package com.diary

import com.domain.entities.Emotion

object DiaryItemsProvider {

    fun provide(): List<DiaryItem> {
        return listOf(
            DiaryItem(
                id = "testId1",
                emotion = Emotion.Rad,
                entryText = "test text 1",
                formattedDate = "12:00, 11 Jul 2023",
            ),
            DiaryItem(
                id = "testId2",
                emotion = Emotion.Good,
                entryText = "test text 2",
                formattedDate = "13:10, 01 Jun 2023",
            ),
            DiaryItem(
                id = "testId3",
                emotion = Emotion.Meh,
                entryText = "test text 3",
                formattedDate = "03:20, 20 May 2023",
            )
        )
    }
}