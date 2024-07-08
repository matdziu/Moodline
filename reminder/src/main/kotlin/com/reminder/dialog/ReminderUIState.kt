package com.reminder.dialog

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import java.time.LocalTime

internal data class ReminderUIState(
    val expanded: Boolean = false,
    val dropdownOptions: ImmutableList<ReminderDropdownOption> = persistentListOf(),
    val selectedDropdownOption: ReminderDropdownOption,
    val selectedTime: LocalTime = LocalTime.NOON
)