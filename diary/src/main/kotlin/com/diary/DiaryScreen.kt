package com.diary

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import com.designsystem.components.DiaryItem
import com.designsystem.components.EmotionSymbol

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

        Column {
            DiaryItem(
                modifier = Modifier.padding(all = 16.dp),
                entryPreviewText = "asidajiodjasioda",
                emotionSymbol = EmotionSymbol.Rad,
                formattedDate = "10:30, 22nd March 2024",
            )
            DiaryItem(
                modifier = Modifier.padding(all = 16.dp),
                entryPreviewText = "asidajiodjasioda",
                emotionSymbol = EmotionSymbol.Good,
                formattedDate = "10:30, 22nd March 2024",
            )
            DiaryItem(
                modifier = Modifier.padding(all = 16.dp),
                entryPreviewText = "asidajiodjasiodaoizdjfiosjdsiosdjsdosdjfisodjsdfoisjfsdiojsfcosdinscoidsndsoiicnoozdfhdsiofshfsoisdhoPPP",
                emotionSymbol = EmotionSymbol.Meh,
                formattedDate = "10:30, 22nd March 2024aoiahadiosfhsdiosdhsiofsdhfoifhsdifhsdoiho",
            )
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