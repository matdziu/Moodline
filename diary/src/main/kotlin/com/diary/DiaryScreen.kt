package com.diary

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun DiaryRoute(
    navigateToAddEntry: () -> Unit,
    diaryViewModel: DiaryViewModel = hiltViewModel(),
) {
    val state by diaryViewModel.state.collectAsStateWithLifecycle()
    val navEvents by diaryViewModel.navigationEvents.collectAsStateWithLifecycle(
        initialValue = DiaryNavigationEvent.Default,
    )

    when (navEvents) {
        DiaryNavigationEvent.GoToAddEntry -> navigateToAddEntry()

        DiaryNavigationEvent.Default -> { /* do nothing */
        }
    }
    
    DiaryScreen(
        diaryUIState = state,
        addEntryButtonPressed = { diaryViewModel.onEvent(DiaryUIEvent.AddEntryButtonPressed) },
    )
}

@Composable
internal fun DiaryScreen(
    diaryUIState: DiaryUIState = DiaryUIState(),
    addEntryButtonPressed: () -> Unit = {},
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "DIARY",
        )
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(all = 16.dp),
            onClick = addEntryButtonPressed,
        ) {
            Icon(Icons.Filled.Add, contentDescription = null)
        }
    }
}