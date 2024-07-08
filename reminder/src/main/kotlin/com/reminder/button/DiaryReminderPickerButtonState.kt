package com.reminder.button

import com.reminder.R

internal data class DiaryReminderPickerButtonState(
    val isReminderSet: Boolean = false,
    val buttonTitleRes: Int = R.string.diary_reminder_picker_button_not_set,
    val toastInfoRes: Int? = R.string.diary_reminder_picker_set_toast_info,
    val showToast: Boolean = false
)