package com.reminder.dialog

import androidx.annotation.StringRes

internal data class ReminderDropdownOption(
    val id: ReminderPeriodId,
    @StringRes val title: Int,
)

internal enum class ReminderPeriodId {
    EVERY_DAY, EVERY_OTHER_DAY, EVERY_WEEK
}