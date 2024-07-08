package com.reminder.button

import androidx.lifecycle.ViewModel
import com.reminder.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class DiaryReminderPickerButtonViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<DiaryReminderPickerButtonState> = MutableStateFlow(
        DiaryReminderPickerButtonState()
    )
    val state: StateFlow<DiaryReminderPickerButtonState> = _state.asStateFlow()


    fun onEvent(event: DiaryReminderPickerButtonUIEvent) {
        when (event) {
            DiaryReminderPickerButtonUIEvent.ButtonPressed -> handleButtonPressed()
            DiaryReminderPickerButtonUIEvent.ToastDisplayed -> handleToastDisplayedEvent()
        }
    }

    private fun handleButtonPressed() {
        val toggledIsReminderSet = !state.value.isReminderSet

        _state.update {
            it.copy(
                isReminderSet = toggledIsReminderSet,
                buttonTitleRes = getButtonTitleRes(toggledIsReminderSet),
                showToast = true,
                toastInfoRes = getToastInfoRes(toggledIsReminderSet)
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