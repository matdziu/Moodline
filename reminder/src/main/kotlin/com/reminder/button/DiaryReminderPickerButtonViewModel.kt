package com.reminder.button

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reminder.R
import com.reminder.button.DiaryReminderPickerButtonUIEvent.ButtonPressed
import com.reminder.button.DiaryReminderPickerButtonUIEvent.DialogDismissed
import com.reminder.button.DiaryReminderPickerButtonUIEvent.ReminderSet
import com.reminder.button.DiaryReminderPickerButtonUIEvent.ToastDisplayed
import com.reminder.work.ReminderNotificationWorkScheduler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DiaryReminderPickerButtonViewModel @Inject constructor(
    private val reminderNotificationWorkScheduler: ReminderNotificationWorkScheduler,
) : ViewModel() {

    private val _state: MutableStateFlow<DiaryReminderPickerButtonState> = MutableStateFlow(
        DiaryReminderPickerButtonState()
    )
    val state: StateFlow<DiaryReminderPickerButtonState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            val isReminderSet = reminderNotificationWorkScheduler.isScheduled()
            _state.update {
                it.copy(
                    isReminderSet = isReminderSet,
                    buttonTitleRes = getButtonTitleRes(isReminderSet),
                    toastInfoRes = getToastInfoRes(isReminderSet),
                )
            }
        }
    }

    fun onEvent(event: DiaryReminderPickerButtonUIEvent) {
        when (event) {
            ButtonPressed -> handleButtonPressed()
            ToastDisplayed -> handleToastDisplayedEvent()
            DialogDismissed -> handleDialogDismissedEvent()
            ReminderSet -> handleReminderSetEvent()
        }
    }

    private fun handleButtonPressed() {
        if (state.value.isReminderSet) {
            cancelReminder()
            _state.update {
                it.copy(
                    isReminderSet = false,
                    buttonTitleRes = getButtonTitleRes(isReminderSet = false),
                    showToast = true,
                    toastInfoRes = getToastInfoRes(isReminderSet = false)
                )
            }
        } else {
            _state.update {
                it.copy(
                    showDialog = true
                )
            }
        }
    }

    private fun cancelReminder() {
        reminderNotificationWorkScheduler.cancel()
    }

    private fun handleReminderSetEvent() {
        _state.update {
            it.copy(
                isReminderSet = true,
                buttonTitleRes = getButtonTitleRes(isReminderSet = true),
                showToast = true,
                toastInfoRes = getToastInfoRes(isReminderSet = true),
                showDialog = false
            )
        }
    }

    private fun handleToastDisplayedEvent() {
        _state.update {
            it.copy(
                showToast = false,
                toastInfoRes = null
            )
        }
    }

    private fun handleDialogDismissedEvent() {
        _state.update {
            it.copy(
                showDialog = false
            )
        }
    }

    private fun getButtonTitleRes(isReminderSet: Boolean): Int {
        return if (isReminderSet) {
            R.string.diary_reminder_picker_button_already_set
        } else {
            R.string.diary_reminder_picker_button_not_set
        }
    }

    private fun getToastInfoRes(isReminderSet: Boolean): Int {
        return if (isReminderSet) {
            R.string.diary_reminder_picker_set_toast_info
        } else {
            R.string.diary_reminder_picker_cancelled_toast_info
        }
    }
}