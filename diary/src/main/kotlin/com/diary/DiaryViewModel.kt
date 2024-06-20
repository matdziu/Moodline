package com.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
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
            DiaryUIEvent.AddEntryButtonPressed -> handleAddEntryButtonPressed()
        }
    }

    private fun handleAddEntryButtonPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(DiaryNavigationEvent.GoToAddEntry)
        }
    }
}