package com.reminder.button

internal sealed interface DiaryReminderPickerButtonUIEvent {

    data object ButtonPressed : DiaryReminderPickerButtonUIEvent

    data object ToastDisplayed: DiaryReminderPickerButtonUIEvent
}