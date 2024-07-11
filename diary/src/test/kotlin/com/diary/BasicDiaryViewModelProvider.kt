package com.diary

import com.domain.entities.DiaryEntry
import com.domain.repositories.DiaryEntriesRepository
import kotlinx.coroutines.flow.flowOf
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class BasicDiaryViewModelProvider(
    diaryEntries: List<DiaryEntry> = listOf()
) {

    val diaryEntriesRepository: DiaryEntriesRepository = mock {
        on { it.getAllFlow() } doReturn flowOf(diaryEntries)
    }

    fun provide(): DiaryViewModel {
        return DiaryViewModel(
            diaryEntriesRepository = diaryEntriesRepository,
        )
    }
}