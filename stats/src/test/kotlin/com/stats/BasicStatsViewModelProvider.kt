package com.stats

import com.domain.entities.DiaryEntry
import com.domain.repositories.DiaryEntriesRepository
import kotlinx.coroutines.flow.flowOf
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class BasicStatsViewModelProvider(
    entriesPerMonthAndYearList: List<EntriesPerMonthAndYear>
) {

    val diaryEntriesRepository: DiaryEntriesRepository = mock<DiaryEntriesRepository> {
        onBlocking { it.getAllFlow() } doReturn flowOf(entriesPerMonthAndYearList.map { it.entries }
            .flatten())
        for (singleEntryPerMonthAndYear in entriesPerMonthAndYearList) {
            val month = singleEntryPerMonthAndYear.monthAndYear.month
            val year = singleEntryPerMonthAndYear.monthAndYear.year
            onBlocking {
                it.getByMonthAndYear(
                    month = month,
                    year = year
                )
            } doReturn singleEntryPerMonthAndYear.entries
        }
    }

    fun provide(): StatsViewModel {
        return StatsViewModel(
            diaryEntriesRepository = diaryEntriesRepository,
        )
    }
}

data class EntriesPerMonthAndYear(
    val entries: List<DiaryEntry>,
    val monthAndYear: MonthAndYear,
)

data class MonthAndYear(
    val month: Int,
    val year: Int
)