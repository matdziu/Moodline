package com.reminder.button

import com.reminder.work.ReminderNotificationWorkScheduler
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

internal class BasicDiaryReminderPickerButtonViewModelProvider(
    isReminderScheduled: Boolean = false,
) {

    val reminderNotificationWorkScheduler: ReminderNotificationWorkScheduler = mock {
        onBlocking { it.isScheduled() } doReturn isReminderScheduled
    }

    fun provide(): DiaryReminderPickerButtonViewModel {
        return DiaryReminderPickerButtonViewModel(
            reminderNotificationWorkScheduler = reminderNotificationWorkScheduler,
        )
    }
}