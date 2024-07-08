package com.reminder.dialog

import androidx.annotation.StringRes

internal data class ReminderDropdownOption(
    val id: ReminderDropdownId,
    @StringRes val title: Int,
)

internal enum class ReminderDropdownId {
    EVERY_DAY, EVERY_OTHER_DAY, EVERY_WEEK
}