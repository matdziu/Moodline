package com.addentry

import app.cash.turbine.turbineScope
import com.commontest.MainDispatcher
import com.commontest.SingleDiaryEntryProvider
import com.domain.entities.Emotion
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import java.time.LocalDate
import java.time.LocalTime

@MainDispatcher
class AddEntryViewModelTest {

    @Test
    fun `should not initialize the screen when entry id is null`() = runTest {
        val provider = BasicAddEntryViewModelProvider(entryId = null)
        val addEntryViewModel = provider.provide()

        turbineScope {
            val state = addEntryViewModel.state.testIn(backgroundScope)

            assertEquals(
                null,
                state.awaitItem().selectedEmotion,
            )

            verify(provider.diaryEntriesRepository, never()).getSingle(any())
        }
    }

    @Test
    fun `should initialize the screen when entry id is passed`() = runTest {
        val testDiaryEntry = SingleDiaryEntryProvider.provide()
        val testEntryId = testDiaryEntry.id

        val provider = BasicAddEntryViewModelProvider(entryId = testEntryId)
        val addEntryViewModel = provider.provide()

        turbineScope {
            val state = addEntryViewModel.state.testIn(backgroundScope)

            assertEquals(
                AddEntryUIState(
                    selectedEmotion = testDiaryEntry.emotion,
                    diaryEntryText = testDiaryEntry.entryText,
                    progress = false,
                    emotionNotSelectedError = false,
                    selectedTime = testDiaryEntry.createdAt.toLocalTime(),
                    selectedDate = testDiaryEntry.createdAt.toLocalDate(),
                ),
                state.awaitItem(),
            )

            verify(provider.diaryEntriesRepository).getSingle(testEntryId)
        }
    }

    @Test
    fun `should select emotion`() = runTest {
        val provider = BasicAddEntryViewModelProvider()
        val addEntryViewModel = provider.provide()

        turbineScope {
            val state = addEntryViewModel.state.testIn(backgroundScope)

            addEntryViewModel.onEvent(AddEntryUIEvent.EmotionSelected(Emotion.Good))

            state.skipItems(1)
            assertEquals(
                Emotion.Good,
                state.awaitItem().selectedEmotion,
            )
        }
    }

    @Test
    fun `should change diary text`() = runTest {
        val newText = "this is an example text diary"

        val provider = BasicAddEntryViewModelProvider()
        val addEntryViewModel = provider.provide()

        turbineScope {
            val state = addEntryViewModel.state.testIn(backgroundScope)

            addEntryViewModel.onEvent(AddEntryUIEvent.DiaryEntryTextChanged(newText))

            state.skipItems(1)
            assertEquals(
                newText,
                state.awaitItem().diaryEntryText,
            )
        }
    }

    @Test
    fun `should change time`() = runTest {
        val newTime = LocalTime.of(3, 10)

        val provider = BasicAddEntryViewModelProvider()
        val addEntryViewModel = provider.provide()

        turbineScope {
            val state = addEntryViewModel.state.testIn(backgroundScope)

            addEntryViewModel.onEvent(AddEntryUIEvent.TimeSelected(newTime))

            state.skipItems(1)
            assertEquals(
                newTime,
                state.awaitItem().selectedTime,
            )
        }
    }

    @Test
    fun `should change date`() = runTest {
        val newDate = LocalDate.of(2023, 7, 11)

        val provider = BasicAddEntryViewModelProvider()
        val addEntryViewModel = provider.provide()

        turbineScope {
            val state = addEntryViewModel.state.testIn(backgroundScope)

            addEntryViewModel.onEvent(AddEntryUIEvent.DateSelected(newDate))

            state.skipItems(1)
            assertEquals(
                newDate,
                state.awaitItem().selectedDate,
            )
        }
    }

    @Test
    fun `should close the screen`() = runTest {
        val provider = BasicAddEntryViewModelProvider()
        val addEntryViewModel = provider.provide()

        turbineScope {
            val navEvents = addEntryViewModel.navigationEvents.testIn(backgroundScope)

            addEntryViewModel.onEvent(AddEntryUIEvent.CancelButtonPressed)

            assertEquals(
                AddEntryNavigationEvent.CloseScreen,
                navEvents.awaitItem(),
            )
        }
    }

    @Test
    fun `should update entry when in edit mode`() = runTest {
        val provider = BasicAddEntryViewModelProvider(
            entryId = "testEntryId"
        )
        val addEntryViewModel = provider.provide()

        addEntryViewModel.onEvent(AddEntryUIEvent.EmotionSelected(Emotion.Good))
        addEntryViewModel.onEvent(AddEntryUIEvent.SaveButtonPressed)

        verify(provider.diaryEntriesRepository).updateEntry(any())
        verify(provider.diaryEntriesRepository, never()).add(any())
    }

    @Test
    fun `should add new entry when not in edit mode`() = runTest {
        val provider = BasicAddEntryViewModelProvider(
            entryId = null
        )
        val addEntryViewModel = provider.provide()

        addEntryViewModel.onEvent(AddEntryUIEvent.EmotionSelected(Emotion.Good))
        addEntryViewModel.onEvent(AddEntryUIEvent.SaveButtonPressed)

        verify(provider.diaryEntriesRepository).add(any())
        verify(provider.diaryEntriesRepository, never()).updateEntry(any())
    }

    @Test
    fun `should not add entry when max length is exceeded`() = runTest {
        val provider = BasicAddEntryViewModelProvider()
        val addEntryViewModel = provider.provide()

        addEntryViewModel.onEvent(AddEntryUIEvent.EmotionSelected(Emotion.Good))
        addEntryViewModel.onEvent(AddEntryUIEvent.DiaryEntryTextChanged("A".repeat(303)))
        addEntryViewModel.onEvent(AddEntryUIEvent.SaveButtonPressed)

        verify(provider.diaryEntriesRepository, never()).add(any())
        verify(provider.diaryEntriesRepository, never()).updateEntry(any())
    }

    @Test
    fun `should not add entry when emotion is not selected`() = runTest {
        val provider = BasicAddEntryViewModelProvider()
        val addEntryViewModel = provider.provide()

        turbineScope {
            val state = addEntryViewModel.state.testIn(backgroundScope)

            addEntryViewModel.onEvent(AddEntryUIEvent.DiaryEntryTextChanged("ABC"))
            addEntryViewModel.onEvent(AddEntryUIEvent.SaveButtonPressed)

            state.skipItems(2)
            assertEquals(
                true,
                state.awaitItem().emotionNotSelectedError,
            )

            verify(provider.diaryEntriesRepository, never()).add(any())
            verify(provider.diaryEntriesRepository, never()).updateEntry(any())
        }
    }

    @Test
    fun `should hide toast when fix errors toast was displayed`() = runTest {
        val provider = BasicAddEntryViewModelProvider()
        val addEntryViewModel = provider.provide()

        turbineScope {
            val state = addEntryViewModel.state.testIn(backgroundScope)

            addEntryViewModel.onEvent(AddEntryUIEvent.SaveButtonPressed)
            addEntryViewModel.onEvent(AddEntryUIEvent.FixErrorsToastDisplayed)

            state.skipItems(1)
            assertEquals(
                true,
                state.awaitItem().showFixErrorsToast,
            )
            assertEquals(
                false,
                state.awaitItem().showFixErrorsToast,
            )
        }
    }
}