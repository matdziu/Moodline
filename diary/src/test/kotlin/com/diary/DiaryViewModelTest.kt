package com.diary

import app.cash.turbine.turbineScope
import com.commontest.DiaryEntriesProvider
import com.commontest.MainDispatcher
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify

@MainDispatcher
class DiaryViewModelTest {

    @Test
    fun `should initialize the screen`() = runTest {
        val diaryEntries = DiaryEntriesProvider.provide()
        val diaryItems = DiaryItemsProvider.provide()
        val provider = BasicDiaryViewModelProvider(
            diaryEntries = diaryEntries
        )
        val diaryViewModel = provider.provide()

        turbineScope {
            val state = diaryViewModel.state.testIn(backgroundScope)

            diaryViewModel.onEvent(DiaryUIEvent.Initialize)

            assertEquals(
                DiaryUIState(),
                state.awaitItem()
            )
            assertEquals(
                DiaryUIState(
                    progress = true,
                ),
                state.awaitItem()
            )
            assertEquals(
                DiaryUIState(
                    progress = false,
                    entries = diaryItems.toImmutableList()
                ),
                state.awaitItem()
            )
        }
    }

    @Test
    fun `should navigate to edit entry`() = runTest {
        val testEntryId = "testEntryId"

        val provider = BasicDiaryViewModelProvider()
        val diaryViewModel = provider.provide()

        turbineScope {
            val navEvents = diaryViewModel.navigationEvents.testIn(backgroundScope)

            diaryViewModel.onEvent(DiaryUIEvent.EditEntry(testEntryId))

            assertEquals(
                DiaryNavigationEvent.EditEntry(testEntryId),
                navEvents.awaitItem(),
            )
        }
    }

    @Test
    fun `should remove entry`() = runTest {
        val testEntryId = "testEntryId"

        val provider = BasicDiaryViewModelProvider()
        val diaryViewModel = provider.provide()

        turbineScope {
            val state = diaryViewModel.state.testIn(backgroundScope)

            diaryViewModel.onEvent(DiaryUIEvent.RemoveEntry(testEntryId))

            state.skipItems(1)
            assertEquals(
                DiaryUIState(
                    progress = true,
                ),
                state.awaitItem(),
            )

            assertEquals(
                DiaryUIState(
                    progress = false,
                ),
                state.awaitItem(),
            )

            verify(provider.diaryEntriesRepository).deleteEntry(testEntryId)
        }
    }
}