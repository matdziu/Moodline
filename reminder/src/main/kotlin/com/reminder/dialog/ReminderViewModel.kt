package com.reminder.dialog

import androidx.lifecycle.ViewModel
import com.reminder.R
import com.reminder.dialog.ReminderUIEvent.ReminderDropdownClicked
import com.reminder.dialog.ReminderUIEvent.ReminderDropdownOnDismiss
import com.reminder.dialog.ReminderUIEvent.ReminderDropdownOptionSelected
import com.reminder.dialog.ReminderUIEvent.TimeSelected
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
internal class ReminderViewModel @Inject constructor() : ViewModel() {

    private val initialDropdownOption = ReminderDropdownOption(
        id = ReminderDropdownId.EVERY_DAY,
        title = R.string.reminder_option_every_day,
    )
    private val dropdownOptions = persistentListOf(
        initialDropdownOption, ReminderDropdownOption(
            id = ReminderDropdownId.EVERY_OTHER_DAY,
            title = R.string.reminder_option_every_other_day,
        ), ReminderDropdownOption(
            id = ReminderDropdownId.EVERY_WEEK,
            title = R.string.reminder_option_every_week,
        )
    )

    private val _state: MutableStateFlow<ReminderUIState> = MutableStateFlow(
        ReminderUIState(
            dropdownOptions = dropdownOptions,
            selectedDropdownOption = initialDropdownOption,
        )
    )
    val state: StateFlow<ReminderUIState> = _state.asStateFlow()

    fun onEvent(event: ReminderUIEvent) {
        when (event) {
            ReminderDropdownClicked -> handleReminderDropdownClicked()
            ReminderDropdownOnDismiss -> handleReminderDropdownOnDismiss()
            is ReminderDropdownOptionSelected -> handleReminderDropdownOptionSelected(
                event.selectedOption,
            )

            is TimeSelected -> handleTimeSelectedEvent(event.localTime)
        }
    }

    private fun handleReminderDropdownClicked() {
        _state.update {
            it.copy(
                expanded = true
            )
        }
    }

    private fun handleReminderDropdownOnDismiss() {
        _state.update {
            it.copy(
                expanded = false
            )
        }
    }

    private fun handleReminderDropdownOptionSelected(selectedOption: ReminderDropdownOption) {
        _state.update {
            it.copy(
                selectedDropdownOption = selectedOption
            )
        }
    }

    private fun handleTimeSelectedEvent(selectedTime: LocalTime) {
        _state.update {
            it.copy(
                selectedTime = selectedTime,
            )
        }
    }
}