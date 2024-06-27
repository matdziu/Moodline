package com.diary

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.common.extensions.toEmotionSymbol
import com.designsystem.components.DiaryListItem

@Composable
internal fun DiaryRoute(
    navigateToAddEntry: () -> Unit,
    onBackButtonPressed: () -> Unit,
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

    LaunchedEffect(Unit) {
        diaryViewModel.onEvent(DiaryUIEvent.Initialize)
    }

    BackHandler {
        onBackButtonPressed()
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

        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = 16.dp,
                bottom = 88.dp,
            )
        ) {
            items(diaryUIState.entries) {
                DiaryListItem(
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                    entryPreviewText = it.entryText,
                    emotionSymbol = it.emotion.toEmotionSymbol(),
                    formattedDate = it.formattedDate
                )
            }
        }

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