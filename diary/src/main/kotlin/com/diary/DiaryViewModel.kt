package com.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.extensions.toDiaryItem
import com.domain.repositories.DiaryEntriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DiaryViewModel @Inject constructor(
    private val diaryEntriesRepository: DiaryEntriesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<DiaryUIState> = MutableStateFlow(DiaryUIState())
    val state: StateFlow<DiaryUIState> = _state.asStateFlow()

    private val _navigationEvents: MutableSharedFlow<DiaryNavigationEvent> = MutableSharedFlow()
    val navigationEvents: SharedFlow<DiaryNavigationEvent> = _navigationEvents.asSharedFlow()

    fun onEvent(event: DiaryUIEvent) {
        when (event) {
            DiaryUIEvent.Initialize -> handleInitializeEvent()
            DiaryUIEvent.AddEntryButtonPressed -> handleAddEntryButtonPressed()
            DiaryUIEvent.Refresh -> handleRefreshEvent()
        }
    }

    private fun handleInitializeEvent() {
        _state.update {
            it.copy(progress = true)
        }
        fetchDiaryEntries()
    }

    private fun handleRefreshEvent() {
        fetchDiaryEntries()
    }

    private fun fetchDiaryEntries() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    progress = false,
                    entries = diaryEntriesRepository
                        .getAll()
                        .map { entry -> entry.toDiaryItem() }
                        .toPersistentList()
                )
            }
        }
    }

    private fun handleAddEntryButtonPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(DiaryNavigationEvent.GoToAddEntry)
        }
    }
}