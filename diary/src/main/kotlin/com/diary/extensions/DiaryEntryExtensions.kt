package com.diary.extensions

import com.common.constants.COMMON_DATE_AND_TIME_FORMAT
import com.diary.DiaryItem
import com.domain.entities.DiaryEntry
import java.time.format.DateTimeFormatter

fun DiaryEntry.toDiaryItem(): DiaryItem {
    val dateTimeFormatter = DateTimeFormatter.ofPattern(COMMON_DATE_AND_TIME_FORMAT)
    return DiaryItem(
        emotion = emotion,
        entryText = entryText,
        formattedDate = createdAt.format(dateTimeFormatter)
    )
}