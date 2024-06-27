package com.diary.extensions

import com.diary.DiaryItem
import com.domain.entities.DiaryEntry
import java.time.format.DateTimeFormatter

fun DiaryEntry.toDiaryItem(): DiaryItem {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy")
    return DiaryItem(
        emotion = emotion,
        entryText = entryText,
        formattedDate = createdAt.format(dateTimeFormatter)
    )
}