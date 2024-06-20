package com.moodline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
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
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<MainUIState> = MutableStateFlow(MainUIState())
    val state: StateFlow<MainUIState> = _state.asStateFlow()

    private val _navigationEvents: MutableSharedFlow<MainNavigationEvent> = MutableSharedFlow()
    val navigationEvents: SharedFlow<MainNavigationEvent> = _navigationEvents.asSharedFlow()

    fun onBottomNavItemClicked(bottomNavItemId: String) {
        when (bottomNavItemId) {
            BottomNavBarId.DIARY.toString() -> handleDiaryBottomNavIconPressed()
            BottomNavBarId.IMPROVE.toString() -> handleImproveBottomNavIconPressed()
            BottomNavBarId.STATS.toString() -> handleStatsBottomNavIconPressed()
        }
    }

    private fun handleDiaryBottomNavIconPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(MainNavigationEvent.GoToDiary)
        }
        _state.update {
            it.copy(
                selectedNavItemId = BottomNavBarId.DIARY,
            )
        }
    }

    private fun handleImproveBottomNavIconPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(MainNavigationEvent.GoToImprove)
        }
        _state.update {
            it.copy(
                selectedNavItemId = BottomNavBarId.IMPROVE,
            )
        }
    }

    private fun handleStatsBottomNavIconPressed() {
        viewModelScope.launch {
            _navigationEvents.emit(MainNavigationEvent.GoToStats)
        }
        _state.update {
            it.copy(
                selectedNavItemId = BottomNavBarId.STATS,
            )
        }
    }
}