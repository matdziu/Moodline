package com.reminder.dialog

import app.cash.turbine.turbineScope
import com.reminder.R
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify
import java.time.LocalTime

class ReminderViewModelTest {

    @Test
    fun `should expand dropdown`() = runTest {
        val provider = BasicReminderViewModelProvider()
        val reminderViewModel = provider.provide()

        turbineScope {
            val state = reminderViewModel.state.testIn(backgroundScope)

            reminderViewModel.onEvent(ReminderUIEvent.ReminderDropdownClicked)

            state.skipItems(1)
            assertEquals(
                true,
                state.awaitItem().expanded,
            )
        }
    }

    @Test
    fun `should hide dropdown`() = runTest {
        val provider = BasicReminderViewModelProvider()
        val reminderViewModel = provider.provide()

        turbineScope {
            val state = reminderViewModel.state.testIn(backgroundScope)

            reminderViewModel.onEvent(ReminderUIEvent.ReminderDropdownClicked)
            reminderViewModel.onEvent(ReminderUIEvent.ReminderDropdownOnDismiss)

            state.skipItems(2)
            assertEquals(
                false,
                state.awaitItem().expanded,
            )
        }
    }

    @Test
    fun `should save selected dropdown option`() = runTest {
        val selectedDropdownOption = ReminderDropdownOption(
            id = ReminderPeriodId.EVERY_OTHER_DAY,
            title = R.string.reminder_option_every_other_day,
        )

        val provider = BasicReminderViewModelProvider()
        val reminderViewModel = provider.provide()

        turbineScope {
            val state = reminderViewModel.state.testIn(backgroundScope)

            reminderViewModel.onEvent(
                ReminderUIEvent.ReminderDropdownOptionSelected(
                    selectedOption = selectedDropdownOption,
                )
            )

            state.skipItems(1)
            assertEquals(
                selectedDropdownOption,
                state.awaitItem().selectedDropdownOption,
            )
        }
    }

    @Test
    fun `should save selected time`() = runTest {
        val selectedTime = LocalTime.of(11, 21)

        val provider = BasicReminderViewModelProvider()
        val reminderViewModel = provider.provide()

        turbineScope {
            val state = reminderViewModel.state.testIn(backgroundScope)

            reminderViewModel.onEvent(
                ReminderUIEvent.TimeSelected(selectedTime)
            )

            state.skipItems(1)
            assertEquals(
                selectedTime,
                state.awaitItem().selectedTime,
            )
        }

    }

    @Test
    fun `should schedule reminder`() = runTest {
        val selectedDropdownOption = ReminderDropdownOption(
            id = ReminderPeriodId.EVERY_OTHER_DAY,
            title = R.string.reminder_option_every_other_day,
        )
        val selectedTime = LocalTime.of(11, 21)

        val provider = BasicReminderViewModelProvider()
        val reminderViewModel = provider.provide()

        reminderViewModel.onEvent(
            ReminderUIEvent.ReminderDropdownOptionSelected(
                selectedOption = selectedDropdownOption,
            )
        )
        reminderViewModel.onEvent(
            ReminderUIEvent.TimeSelected(selectedTime)
        )
        reminderViewModel.onEvent(ReminderUIEvent.SaveButtonPressed)

        verify(provider.reminderNotificationWorkScheduler).schedule(
            reminderPeriodId = ReminderPeriodId.EVERY_OTHER_DAY,
            time = selectedTime,
        )
    }
}