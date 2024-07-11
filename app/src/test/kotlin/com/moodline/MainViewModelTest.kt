package com.moodline

import app.cash.turbine.turbineScope
import com.commontest.MainDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.verify

@MainDispatcher
class MainViewModelTest {

    @Test
    fun `should navigate to diary`() = runTest {
        val provider = BasicMainViewModelProvider()
        val mainViewModel = provider.provide()

        turbineScope {
            val navEvents = mainViewModel.navigationEvents.testIn(backgroundScope)
            val state = mainViewModel.state.testIn(backgroundScope)

            mainViewModel.onBottomNavItemClicked(BottomNavBarId.DIARY.toString())

            assertEquals(
                MainUIState(
                    selectedNavItemId = BottomNavBarId.DIARY,
                ),
                state.awaitItem(),
            )

            assertEquals(
                MainNavigationEvent.GoToDiary,
                navEvents.awaitItem(),
            )
        }

        verify(provider.savedStateHandle)[SELECTED_NAV_ITEM_ID_KEY] = BottomNavBarId.DIARY
    }

    @Test
    fun `should navigate to stats`() = runTest {
        val provider = BasicMainViewModelProvider()
        val mainViewModel = provider.provide()

        turbineScope {
            val navEvents = mainViewModel.navigationEvents.testIn(backgroundScope)
            val state = mainViewModel.state.testIn(backgroundScope)

            mainViewModel.onBottomNavItemClicked(BottomNavBarId.STATS.toString())

            assertEquals(
                MainUIState(
                    selectedNavItemId = BottomNavBarId.DIARY,
                ),
                state.awaitItem(),
            )
            assertEquals(
                MainUIState(
                    selectedNavItemId = BottomNavBarId.STATS,
                ),
                state.awaitItem(),
            )

            assertEquals(
                MainNavigationEvent.GoToStats,
                navEvents.awaitItem(),
            )

            verify(provider.savedStateHandle)[SELECTED_NAV_ITEM_ID_KEY] = BottomNavBarId.STATS
        }
    }

    @Test
    fun `should navigate to improve`() = runTest {
        val provider = BasicMainViewModelProvider()
        val mainViewModel = provider.provide()

        turbineScope {
            val navEvents = mainViewModel.navigationEvents.testIn(backgroundScope)
            val state = mainViewModel.state.testIn(backgroundScope)

            mainViewModel.onBottomNavItemClicked(BottomNavBarId.IMPROVE.toString())

            assertEquals(
                MainUIState(
                    selectedNavItemId = BottomNavBarId.DIARY,
                ),
                state.awaitItem(),
            )
            assertEquals(
                MainUIState(
                    selectedNavItemId = BottomNavBarId.IMPROVE,
                ),
                state.awaitItem(),
            )

            assertEquals(
                MainNavigationEvent.GoToImprove,
                navEvents.awaitItem(),
            )

            verify(provider.savedStateHandle)[SELECTED_NAV_ITEM_ID_KEY] = BottomNavBarId.IMPROVE
        }
    }
}