package com.reminder.dialog

import java.time.LocalTime

internal sealed interface ReminderUIEvent {

    data object ReminderDropdownClicked : ReminderUIEvent

    data object ReminderDropdownOnDismiss : ReminderUIEvent

    data class ReminderDropdownOptionSelected(
        val selectedOption: ReminderDropdownOption,
    ) : ReminderUIEvent

    data class TimeSelected(val localTime: LocalTime) : ReminderUIEvent
}