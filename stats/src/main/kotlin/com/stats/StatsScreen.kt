package com.stats

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.components.FullScreenProgressIndicator
import com.designsystem.components.StatsListItem

@Composable
internal fun StatsRoute(onBackButtonPressed: () -> Unit) {

    val statsViewModel: StatsViewModel = hiltViewModel()
    val state by statsViewModel.state.collectAsStateWithLifecycle()

    DisposableEffect(Unit) {
        statsViewModel.onEvent(StatsUIEvent.Initialize)

        onDispose { statsViewModel.onEvent(StatsUIEvent.OnDispose) }
    }

    BackHandler {
        onBackButtonPressed()
    }
    StatsScreen(
        statsUIState = state,
    )
}

@Composable
internal fun StatsScreen(
    statsUIState: StatsUIState,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (statsUIState.statsItems.isEmpty() && !statsUIState.progress) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp),
                text = stringResource(id = R.string.empty_stats_text),
                textAlign = TextAlign.Center
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    vertical = 32.dp,
                    horizontal = 16.dp
                )
            ) {
                items(statsUIState.statsItems) {
                    StatsListItem(
                        modifier = Modifier.padding(vertical = 8.dp),
                        label = it.label,
                        radCount = it.radCount,
                        goodCount = it.goodCount,
                        mehCount = it.mehCount,
                        badCount = it.badCount,
                        awfulCount = it.awfulCount,
                    )
                }
            }
        }


        if (statsUIState.progress) {
            FullScreenProgressIndicator()
        }
    }
}