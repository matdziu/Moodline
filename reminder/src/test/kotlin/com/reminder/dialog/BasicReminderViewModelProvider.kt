package com.reminder.dialog

import com.reminder.work.ReminderNotificationWorkScheduler
import org.mockito.kotlin.mock

internal class BasicReminderViewModelProvider {

    val reminderNotificationWorkScheduler: ReminderNotificationWorkScheduler = mock()

    fun provide(): ReminderViewModel {
        return ReminderViewModel(
            reminderNotificationWorkScheduler = reminderNotificationWorkScheduler,
        )
    }
}