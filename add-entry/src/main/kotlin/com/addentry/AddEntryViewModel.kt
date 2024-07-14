package com.addentry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.addentry.navigation.entryIdArgument
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
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddEntryViewModel @Inject constructor(
    private val diaryEntriesRepository: DiaryEntriesRepository,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state: MutableStateFlow<AddEntryUIState> = MutableStateFlow(AddEntryUIState())
    val state: StateFlow<AddEntryUIState> = _state.asStateFlow()

    private val _navigationEvents: MutableSharedFlow<AddEntryNavigationEvent> = MutableSharedFlow()
    val navigationEvents: SharedFlow<AddEntryNavigationEvent> = _navigationEvents.asSharedFlow()

    private var entryAddingInProgress = false

    private var isInEditMode = false
    private var editedDiaryEntry: DiaryEntry? = null

    init {
        onEvent(AddEntryUIEvent.Initialize)
    }

    fun onEvent(event: AddEntryUIEvent) {
        when (event) {
            AddEntryUIEvent.Initialize -> handleInitializeEvent()
            is AddEntryUIEvent.EmotionSelected -> handleEmotionSelected(event.emotion)
            is AddEntryUIEvent.DiaryEntryTextChanged -> handleDiaryEntryTextChanged(event.newText)
            AddEntryUIEvent.SaveButtonPressed -> {
                if (!entryAddingInProgress) {
                    entryAddingInProgress = true
                    handleSaveButtonPressed()
                }
            }

            AddEntryUIEvent.CancelButtonPressed -> handleCancelButtonPressed()
            is AddEntryUIEvent.DateSelected -> handleDateSelected(event.localDate)
            is AddEntryUIEvent.TimeSelected -> handleTimeSelected(event.localTime)
            AddEntryUIEvent.FixErrorsToastDisplayed -> handleFixErrorsToastDisplayed()
        }
    }

    private fun handleInitializeEvent() {
        val entryId: String? = savedStateHandle[entryIdArgument]
        if (entryId != null) {
            isInEditMode = true
            viewModelScope.launch {
                val currentDiaryEntry = diaryEntriesRepository.getSingle(entryId)
                editedDiaryEntry = currentDiaryEntry
                _state.update {
                    it.copy(
                        selectedEmotion = currentDiaryEntry.emotion,
                        diaryEntryText = currentDiaryEntry.entryText,
                        selectedTime = currentDiaryEntry.createdAt.toLocalTime(),
                        selectedDate = currentDiaryEntry.createdAt.toLocalDate()
                    )
                }
            }
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

    private fun handleSaveButtonPressed() = viewModelScope.launch {
        val currentState = state.value
        val selectedEmotion = currentState.selectedEmotion
        val diaryEntryText = currentState.diaryEntryText
        val selectedTime = currentState.selectedTime
        val selectedDate = currentState.selectedDate

        if (selectedEmotion == null) {
            _state.update {
                it.copy(
                    emotionNotSelectedError = true,
                    showFixErrorsToast = true,
                )
            }
            entryAddingInProgress = false
            return@launch
        }

        if (diaryEntryText.length > MAX_CHAR_LENGTH_OF_DIARY_ENTRY) {
            _state.update {
                it.copy(
                    showFixErrorsToast = true,
                )
            }
            entryAddingInProgress = false
            return@launch
        }

        _state.update {
            it.copy(
                progress = true,
            )
        }

        val currentDiaryEntry = editedDiaryEntry
        if (isInEditMode && currentDiaryEntry != null) {
            diaryEntriesRepository.updateEntry(
                DiaryEntry(
                    id = currentDiaryEntry.id,
                    emotion = selectedEmotion,
                    entryText = diaryEntryText,
                    createdAt = LocalDateTime.of(selectedDate, selectedTime),
                )
            )
        } else {
            diaryEntriesRepository.add(
                DiaryEntry(
                    id = UUID.randomUUID().toString(),
                    emotion = selectedEmotion,
                    entryText = diaryEntryText,
                    createdAt = LocalDateTime.of(selectedDate, selectedTime),
                )
            )
        }

        entryAddingInProgress = false

        _navigationEvents.emit(AddEntryNavigationEvent.CloseScreen)
    }

    private fun handleDateSelected(localDate: LocalDate) {
        _state.update {
            it.copy(
                selectedDate = localDate
            )
        }
    }

    private fun handleTimeSelected(localTime: LocalTime) {
        _state.update {
            it.copy(
                selectedTime = localTime
            )
        }
    }

    private fun handleCancelButtonPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(AddEntryNavigationEvent.CloseScreen)
        }
    }

    private fun handleFixErrorsToastDisplayed() {
        _state.update {
            it.copy(
                showFixErrorsToast = false,
            )
        }
    }
}