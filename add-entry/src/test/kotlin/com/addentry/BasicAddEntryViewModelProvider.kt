package com.addentry

import androidx.lifecycle.SavedStateHandle
import com.addentry.navigation.entryIdArgument
import com.commontest.SingleDiaryEntryProvider
import com.domain.entities.DiaryEntry
import com.domain.repositories.DiaryEntriesRepository
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class BasicAddEntryViewModelProvider(
    entryId: String? = null,
    currentDiaryEntry: DiaryEntry = SingleDiaryEntryProvider.provide(),
) {

    val savedStateHandle: SavedStateHandle = mock {
        on<String?> { it[entryIdArgument] } doReturn entryId
    }
    val diaryEntriesRepository: DiaryEntriesRepository = mock {
        onBlocking { it.getSingle(any()) } doReturn currentDiaryEntry
    }

    fun provide(): AddEntryViewModel {
        return AddEntryViewModel(
            diaryEntriesRepository = diaryEntriesRepository,
            savedStateHandle = savedStateHandle,
        )
    }
}