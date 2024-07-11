package com.stats

import app.cash.turbine.turbineScope
import com.commontest.MainDispatcher
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

@MainDispatcher
class StatsViewModelTest {

    @ParameterizedTest
    @ArgumentsSource(StatsTestArgumentsProvider::class)
    fun `should return correct stats items`(statsTestArgument: StatsTestArgument) = runTest {
        val provider = BasicStatsViewModelProvider(
            statsTestArgument.entriesPerMonthAndYearList
        )
        val statsViewModel = provider.provide()

        turbineScope {
            val state = statsViewModel.state.testIn(backgroundScope)

            statsViewModel.onEvent(StatsUIEvent.Initialize)

            state.skipItems(1)
            assertEquals(
                StatsUIState(
                    statsItems = statsTestArgument.expectedStatsItemsList.toImmutableList(),
                ), state.awaitItem()
            )
        }
    }

}