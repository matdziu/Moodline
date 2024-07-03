package com.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diary.extensions.toDiaryItem
import com.domain.entities.DiaryEntry
import com.domain.repositories.DiaryEntriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
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
            DiaryUIEvent.Refresh -> handleRefreshEvent()
            is DiaryUIEvent.EditEntry -> handleEditEntryEvent(event.diaryEntryId)
            is DiaryUIEvent.RemoveEntry -> handleRemoveEntryEvent(event.diaryEntryId)
        }
    }

    private fun handleInitializeEvent() {
        _state.update {
            it.copy(progress = true)
        }
        fetchDiaryEntries()

        viewModelScope.launch {
            diaryEntriesRepository.getAllFlow().collect { newDiaryEntries ->
                _state.update {
                    it.copy(
                        entries = newDiaryEntries.diaryEntriesToListItems()
                    )
                }
            }
        }
    }

    private fun handleRefreshEvent() {
        fetchDiaryEntries()
    }

    private fun handleEditEntryEvent(diaryEntryId: String) {
        viewModelScope.launch {
            _navigationEvents.emit(DiaryNavigationEvent.EditEntry(diaryEntryId))
        }
    }

    private fun handleRemoveEntryEvent(diaryEntryId: String) {
        _state.update {
            it.copy(
                progress = true
            )
        }

        viewModelScope.launch {

            diaryEntriesRepository.deleteEntry(diaryEntryId)

            _state.update {
                it.copy(
                    progress = false
                )
            }

        }
    }

    private fun fetchDiaryEntries() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    progress = false,
                    entries = diaryEntriesRepository.getAll().diaryEntriesToListItems()
                )
            }
        }
    }

    private fun List<DiaryEntry>.diaryEntriesToListItems(): ImmutableList<DiaryItem> {
        return sortedByDescending { entry -> entry.createdAt }.map { entry -> entry.toDiaryItem() }
            .toPersistentList()
    }
}