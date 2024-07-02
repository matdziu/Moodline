package com.addentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.common.constants.MAX_CHAR_LENGTH_OF_DIARY_ENTRY
import com.domain.entities.DiaryEntry
import com.domain.entities.Emotion
import com.domain.repositories.DiaryEntriesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class AddEntryViewModel @Inject constructor(
    private val diaryEntriesRepository: DiaryEntriesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<AddEntryUIState> = MutableStateFlow(AddEntryUIState())
    val state: StateFlow<AddEntryUIState> = _state.asStateFlow()

    private val _navigationEvents: MutableSharedFlow<AddEntryNavigationEvent> = MutableSharedFlow()
    val navigationEvents: SharedFlow<AddEntryNavigationEvent> = _navigationEvents.asSharedFlow()

    private var entryAddingInProgress = false

    private var selectedLocalDate = LocalDate.now()
    private var selectedLocalTime = LocalTime.now()

    fun onEvent(event: AddEntryUIEvent) {
        when (event) {
            is AddEntryUIEvent.EmotionSelected -> handleEmotionSelected(event.emotion)
            is AddEntryUIEvent.DiaryEntryTextChanged -> handleDiaryEntryTextChanged(event.newText)
            AddEntryUIEvent.AddButtonPressed -> {
                if (!entryAddingInProgress) {
                    entryAddingInProgress = true
                    handleAddButtonPressed()
                }
            }

            AddEntryUIEvent.CancelButtonPressed -> handleCancelButtonPressed()
            is AddEntryUIEvent.DateSelected -> handleDateSelected(event.localDate)
            is AddEntryUIEvent.TimeSelected -> handleTimeSelected(event.localTime)
        }
    }

    private fun handleEmotionSelected(emotion: Emotion) {
        _state.update {
            it.copy(
                selectedEmotion = emotion,
                emotionNotSelectedError = false,
            )
        }
    }

    private fun handleDiaryEntryTextChanged(newText: String) {
        _state.update {
            it.copy(
                diaryEntryText = newText,
            )
        }
    }

    private fun handleAddButtonPressed() = viewModelScope.launch {
        val currentState = state.value
        val selectedEmotion = currentState.selectedEmotion
        val diaryEntryText = currentState.diaryEntryText

        if (selectedEmotion == null) {
            _state.update {
                it.copy(
                    emotionNotSelectedError = true,
                )
            }
            entryAddingInProgress = false
            return@launch
        }

        if (diaryEntryText.length > MAX_CHAR_LENGTH_OF_DIARY_ENTRY) {
            entryAddingInProgress = false
            return@launch
        }

        _state.update {
            it.copy(
                progress = true,
            )
        }

        diaryEntriesRepository.add(
            DiaryEntry(
                emotion = selectedEmotion,
                entryText = diaryEntryText,
                createdAt = LocalDateTime.of(selectedLocalDate, selectedLocalTime),
            )
        )

        entryAddingInProgress = false

        _navigationEvents.emit(AddEntryNavigationEvent.CloseScreen)
    }

    private fun handleDateSelected(localDate: LocalDate) {
        selectedLocalDate = localDate
    }

    private fun handleTimeSelected(localTime: LocalTime) {
        selectedLocalTime = localTime
    }

    private fun handleCancelButtonPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(AddEntryNavigationEvent.CloseScreen)
        }
    }
}