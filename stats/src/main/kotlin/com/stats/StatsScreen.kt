package com.stats

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.components.OneTimeLaunchedEffect

@Composable
internal fun StatsRoute(onBackButtonPressed: () -> Unit) {

    val statsViewModel: StatsViewModel = hiltViewModel()
    val state by statsViewModel.state.collectAsStateWithLifecycle()

    OneTimeLaunchedEffect {
        statsViewModel.onEvent(StatsUIEvent.Initialize)
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
    statsUIState: StatsUIState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = statsUIState.debugText,
            textAlign = TextAlign.Center,
        )
    }
}