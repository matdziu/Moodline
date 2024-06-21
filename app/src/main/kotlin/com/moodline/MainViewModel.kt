package com.moodline

import androidx.lifecycle.SavedStateHandle
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

const val SELECTED_NAV_ITEM_ID_KEY = "selected-nav-item-id"

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state: MutableStateFlow<MainUIState> = MutableStateFlow(
        MainUIState(
            selectedNavItemId = savedStateHandle[SELECTED_NAV_ITEM_ID_KEY] ?: BottomNavBarId.DIARY
        )
    )
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
        savedStateHandle[SELECTED_NAV_ITEM_ID_KEY] = BottomNavBarId.DIARY
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
        savedStateHandle[SELECTED_NAV_ITEM_ID_KEY] = BottomNavBarId.IMPROVE
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
        savedStateHandle[SELECTED_NAV_ITEM_ID_KEY] = BottomNavBarId.STATS
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