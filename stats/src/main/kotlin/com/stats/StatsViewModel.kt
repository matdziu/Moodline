package com.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.entities.DiaryEntry
import com.domain.entities.Emotion
import com.domain.repositories.DiaryEntriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class StatsViewModel @Inject constructor(
    private val diaryEntriesRepository: DiaryEntriesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<StatsUIState> = MutableStateFlow(StatsUIState())
    val state: StateFlow<StatsUIState> = _state.asStateFlow()

    fun onEvent(event: StatsUIEvent) {
        when (event) {
            StatsUIEvent.Initialize -> handleInitializeEvent()
        }
    }

    private fun handleInitializeEvent() {
        viewModelScope.launch {

            diaryEntriesRepository.getAllFlow().collect { allEntries ->
                createAllStatsItems(
                    allEntries = allEntries,
                )
            }

        }
    }

    private suspend fun createAllStatsItems(
        allEntries: List<DiaryEntry>
    ) {
        val allEntriesSize = allEntries.size
        var currentlyFilteredEntriesNumber = 0
        val allStats: MutableList<StatsItem> = mutableListOf()
        var currentCreatedAt = allEntries.first().createdAt

        if (allEntries.isNotEmpty()) {

            while (currentlyFilteredEntriesNumber < allEntriesSize) {

                val currentCreatedAtMonth = currentCreatedAt.monthValue
                val currentCreatedAtYear = currentCreatedAt.year

                val entriesByMonthAndYear = diaryEntriesRepository.getByMonthAndYearFlow(
                    month = currentCreatedAtMonth,
                    year = currentCreatedAtYear
                )

                if (entriesByMonthAndYear.isEmpty()) continue

                val statsItem = createSingleStatsItem(
                    month = currentCreatedAtMonth,
                    year = currentCreatedAtYear,
                    filteredEntries = entriesByMonthAndYear
                )

                allStats.add(statsItem)
                currentlyFilteredEntriesNumber += entriesByMonthAndYear.size

                if (currentlyFilteredEntriesNumber < allEntriesSize) {
                    currentCreatedAt = allEntries[currentlyFilteredEntriesNumber].createdAt
                }

            }

            var debugText = ""

            allStats.forEach {
                debugText += "\n\n$it"
            }

            _state.update {
                it.copy(
                    debugText = debugText
                )
            }


        } else {

            _state.update {
                it.copy(
                    debugText = "EMPTY"
                )
            }

        }
    }

    private fun createSingleStatsItem(
        month: Int,
        year: Int,
        filteredEntries: List<DiaryEntry>
    ): StatsItem {
        var numberOfRad = 0
        var numberOfGood = 0
        var numberOfMeh = 0
        var numberOfBad = 0
        var numberOfAwful = 0

        for (entry in filteredEntries) {

            when (entry.emotion) {
                Emotion.Awful -> numberOfAwful += 1
                Emotion.Bad -> numberOfBad += 1
                Emotion.Good -> numberOfGood += 1
                Emotion.Meh -> numberOfMeh += 1
                Emotion.Rad -> numberOfRad += 1
            }

        }

        return StatsItem(
            month = month,
            year = year,
            numberOfRad = numberOfRad,
            numberOfGood = numberOfGood,
            numberOfMeh = numberOfMeh,
            numberOfBad = numberOfBad,
            numberOfAwful = numberOfAwful,
        )
    }
}