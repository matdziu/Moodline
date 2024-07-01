package com.addentry

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddEntryViewModel @Inject constructor(
    private val diaryEntriesRepository: DiaryEntriesRepository,
) : ViewModel() {

    private val _state: MutableStateFlow<AddEntryUIState> = MutableStateFlow(AddEntryUIState())
    val state: StateFlow<AddEntryUIState> = _state.asStateFlow()

    private val _navigationEvents: MutableSharedFlow<AddEntryNavigationEvent> = MutableSharedFlow()
    val navigationEvents: SharedFlow<AddEntryNavigationEvent> = _navigationEvents.asSharedFlow()

    fun onEvent(event: AddEntryUIEvent) {
        when (event) {
            is AddEntryUIEvent.EmotionSelected -> handleEmotionSelected(event.emotion)
            is AddEntryUIEvent.DiaryEntryTextChanged -> handleDiaryEntryTextChanged(event.newText)
            AddEntryUIEvent.AddButtonPressed -> handleAddButtonPressed()
            AddEntryUIEvent.CancelButtonPressed -> handleCancelButtonPressed()
        }
    }

    private fun handleEmotionSelected(emotion: Emotion) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedEmotion = emotion,
                    emotionNotSelectedError = false,
                )
            }
        }
    }

    private fun handleDiaryEntryTextChanged(newText: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    diaryEntryText = newText,
                )
            }
        }
    }

    private fun handleAddButtonPressed() {
        val currentState = state.value
        val selectedEmotion = currentState.selectedEmotion
        val diaryEntryText = currentState.diaryEntryText

        viewModelScope.launch {

            if (selectedEmotion == null) {

                _state.update {
                    it.copy(
                        progress = false,
                        emotionNotSelectedError = true,
                    )
                }

            } else {

                _state.update {
                    it.copy(
                        progress = true,
                    )
                }

                diaryEntriesRepository.add(
                    DiaryEntry(
                        emotion = selectedEmotion,
                        entryText = diaryEntryText,
                        createdAt = LocalDateTime.now(),
                    )
                )

                _state.update {
                    it.copy(progress = false)
                }

                _navigationEvents.emit(AddEntryNavigationEvent.CloseScreen)
            }
        }
    }

    private fun handleCancelButtonPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(AddEntryNavigationEvent.CloseScreen)
        }
    }
}