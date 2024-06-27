package com.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.entities.Emotion
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
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
class DiaryViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<DiaryUIState> = MutableStateFlow(DiaryUIState())
    val state: StateFlow<DiaryUIState> = _state.asStateFlow()

    private val _navigationEvents: MutableSharedFlow<DiaryNavigationEvent> = MutableSharedFlow()
    val navigationEvents: SharedFlow<DiaryNavigationEvent> = _navigationEvents.asSharedFlow()

    fun onEvent(event: DiaryUIEvent) {
        when (event) {
            DiaryUIEvent.Initialize -> handleInitializeEvent()
            DiaryUIEvent.AddEntryButtonPressed -> handleAddEntryButtonPressed()
        }
    }

    private fun handleInitializeEvent() {
        _state.update {
            it.copy(
                entries = persistentListOf(
                    DiaryItem(
                        emotion = Emotion.Rad,
                        entryText = "test1",
                        formattedDate = "10:30, 22nd March 1994"
                    ),
                    DiaryItem(
                        emotion = Emotion.Rad,
                        entryText = "test2",
                        formattedDate = "10:30, 22nd March 1994"
                    ),
                    DiaryItem(
                        emotion = Emotion.Rad,
                        entryText = "test3",
                        formattedDate = "10:30, 22nd March 1994"
                    ),
                    DiaryItem(
                        emotion = Emotion.Good,
                        entryText = "test4",
                        formattedDate = "10:30, 22nd March 1994"
                    ),
                    DiaryItem(
                        emotion = Emotion.Good,
                        entryText = "test5",
                        formattedDate = "10:30, 22nd March 1994"
                    ),
                    DiaryItem(
                        emotion = Emotion.Good,
                        entryText = "test6",
                        formattedDate = "10:30, 22nd March 1994"
                    ),
                    DiaryItem(
                        emotion = Emotion.Awful,
                        entryText = "test7",
                        formattedDate = "10:30, 22nd March 1994"
                    ),
                    DiaryItem(
                        emotion = Emotion.Awful,
                        entryText = "test8",
                        formattedDate = "10:30, 22nd March 1994"
                    ),
                    DiaryItem(
                        emotion = Emotion.Awful,
                        entryText = "test9",
                        formattedDate = "10:30, 22nd March 1994"
                    )
                )
            )
        }
    }

    private fun handleAddEntryButtonPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(DiaryNavigationEvent.GoToAddEntry)
        }
    }
}