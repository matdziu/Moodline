package com.reminder.button

import app.cash.turbine.turbineScope
import com.commontest.MainDispatcher
import com.reminder.R
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.never
import org.mockito.kotlin.verify

@MainDispatcher
class DiaryReminderPickerButtonViewModelTest {

    @Test
    fun `should provide correct copy when reminder is set`() = runTest {
        val provider = BasicDiaryReminderPickerButtonViewModelProvider(isReminderScheduled = true)
        val viewModel = provider.provide()

        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)

            assertEquals(
                DiaryReminderPickerButtonState(
                    isReminderSet = true,
                    buttonTitleRes = R.string.diary_reminder_picker_button_already_set,
                    toastInfoRes = R.string.diary_reminder_picker_set_toast_info,
                ),
                state.awaitItem(),
            )
        }
    }

    @Test
    fun `should provide correct copy when reminder is not set`() = runTest {
        val provider = BasicDiaryReminderPickerButtonViewModelProvider(isReminderScheduled = false)
        val viewModel = provider.provide()

        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)

            assertEquals(
                DiaryReminderPickerButtonState(
                    isReminderSet = false,
                    buttonTitleRes = R.string.diary_reminder_picker_button_not_set,
                    toastInfoRes = R.string.diary_reminder_picker_cancelled_toast_info,
                ),
                state.awaitItem(),
            )
        }
    }

    @Test
    fun `should cancel when reminder is set`() = runTest {
        val provider = BasicDiaryReminderPickerButtonViewModelProvider(isReminderScheduled = true)
        val viewModel = provider.provide()

        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)

            viewModel.onEvent(DiaryReminderPickerButtonUIEvent.ButtonPressed)

            state.skipItems(1)
            assertEquals(
                DiaryReminderPickerButtonState(
                    showDialog = false,
                    isReminderSet = false,
                    buttonTitleRes = R.string.diary_reminder_picker_button_not_set,
                    toastInfoRes = R.string.diary_reminder_picker_cancelled_toast_info,
                    showToast = true,
                ),
                state.awaitItem(),
            )

            verify(provider.reminderNotificationWorkScheduler).cancel()
        }
    }

    @Test
    fun `should show dialog when reminder is not set`() = runTest {
        val provider = BasicDiaryReminderPickerButtonViewModelProvider(isReminderScheduled = false)
        val viewModel = provider.provide()

        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)

            viewModel.onEvent(DiaryReminderPickerButtonUIEvent.ButtonPressed)

            state.skipItems(1)
            assertEquals(
                true,
                state.awaitItem().showDialog,
            )

            verify(provider.reminderNotificationWorkScheduler, never()).cancel()
        }
    }

    @Test
    fun `should null toast after it is shown`() = runTest {
        val provider = BasicDiaryReminderPickerButtonViewModelProvider()
        val viewModel = provider.provide()

        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)

            viewModel.onEvent(DiaryReminderPickerButtonUIEvent.ReminderSet)
            viewModel.onEvent(DiaryReminderPickerButtonUIEvent.ToastDisplayed)

            state.skipItems(2)
            val stateItem = state.awaitItem()
            assertEquals(
                false,
                stateItem.showToast,
            )
            assertEquals(
                null,
                stateItem.toastInfoRes,
            )
        }
    }

    @Test
    fun `should dismiss dialog`() = runTest {
        val provider = BasicDiaryReminderPickerButtonViewModelProvider(isReminderScheduled = false)
        val viewModel = provider.provide()

        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)

            viewModel.onEvent(DiaryReminderPickerButtonUIEvent.ButtonPressed)
            viewModel.onEvent(DiaryReminderPickerButtonUIEvent.DialogDismissed)

            state.skipItems(2)
            assertEquals(
                false,
                state.awaitItem().showDialog,
            )
        }
    }

    @Test
    fun `should set reminder`() = runTest {
        val provider = BasicDiaryReminderPickerButtonViewModelProvider()
        val viewModel = provider.provide()

        turbineScope {
            val state = viewModel.state.testIn(backgroundScope)

            viewModel.onEvent(DiaryReminderPickerButtonUIEvent.ReminderSet)

            state.skipItems(1)
            assertEquals(
                DiaryReminderPickerButtonState(
                    isReminderSet = true,
                    buttonTitleRes = R.string.diary_reminder_picker_button_already_set,
                    showToast = true,
                    toastInfoRes = R.string.diary_reminder_picker_set_toast_info,
                    showDialog = false,
                ), state.awaitItem()
            )
        }
    }
}