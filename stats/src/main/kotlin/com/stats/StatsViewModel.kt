package com.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.constants.STATS_ITEM_DATE_FORMAT
import com.domain.entities.DiaryEntry
import com.domain.entities.Emotion
import com.domain.repositories.DiaryEntriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
internal class StatsViewModel @Inject constructor(
    private val diaryEntriesRepository: DiaryEntriesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<StatsUIState> = MutableStateFlow(StatsUIState())
    val state: StateFlow<StatsUIState> = _state.asStateFlow()

    private var listenToAllEntriesJob: Job? = null

    private val dateTimeFormatter = DateTimeFormatter.ofPattern(STATS_ITEM_DATE_FORMAT)

    fun onEvent(event: StatsUIEvent) {
        when (event) {
            StatsUIEvent.Initialize -> handleInitializeEvent()
            StatsUIEvent.OnDispose -> handleOnDisposeEvent()
        }
    }

    private fun handleInitializeEvent() {
        listenToAllEntriesJob = viewModelScope.launch {

            _state.update {
                it.copy(
                    progress = true,
                )
            }

            diaryEntriesRepository.getAllFlow().collect { allEntries ->
                createAllStatsItems(
                    allEntries = allEntries,
                )
            }

        }
    }

    private fun handleOnDisposeEvent() {
        listenToAllEntriesJob?.cancel()
    }

    private suspend fun createAllStatsItems(
        allEntries: List<DiaryEntry>
    ) {
        val allEntriesSize = allEntries.size
        var currentlyFilteredEntriesNumber = 0
        val allStats: MutableList<StatsItem> = mutableListOf()

        if (allEntries.isNotEmpty()) {
            var currentCreatedAt = allEntries.first().createdAt

            while (currentlyFilteredEntriesNumber < allEntriesSize) {

                val currentCreatedAtMonth = currentCreatedAt.monthValue
                val currentCreatedAtYear = currentCreatedAt.year

                val entriesByMonthAndYear = diaryEntriesRepository.getByMonthAndYear(
                    month = currentCreatedAtMonth, year = currentCreatedAtYear
                )

                val statsItem = createSingleStatsItem(
                    label = currentCreatedAt.format(dateTimeFormatter),
                    filteredEntries = entriesByMonthAndYear
                )

                allStats.add(statsItem)
                currentlyFilteredEntriesNumber += entriesByMonthAndYear.size

                if (currentlyFilteredEntriesNumber < allEntriesSize) {
                    currentCreatedAt = allEntries[currentlyFilteredEntriesNumber].createdAt
                }

            }

            _state.update {
                it.copy(
                    progress = false,
                    statsItems = allStats.toImmutableList(),
                )
            }


        } else {

            _state.update {
                it.copy(
                    progress = false,
                    statsItems = persistentListOf()
                )
            }

        }
    }

    private fun createSingleStatsItem(
        label: String,
        filteredEntries: List<DiaryEntry>,
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
            label = label,
            radCount = numberOfRad,
            goodCount = numberOfGood,
            mehCount = numberOfMeh,
            badCount = numberOfBad,
            awfulCount = numberOfAwful,
        )
    }
}