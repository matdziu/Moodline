package com.addentry

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.common.constants.MAX_CHAR_LENGTH_OF_DIARY_ENTRY
import com.designsystem.components.EmotionPicker
import com.designsystem.components.EmotionSymbol
import com.designsystem.components.FullScreenProgressIndicator
import com.designsystem.components.MoodlineButton
import com.designsystem.components.MoodlineOutlinedButton
import com.designsystem.components.TextFieldWithCharLimit
import com.designsystem.extensions.toEmotion
import com.designsystem.extensions.toEmotionSymbol

@Composable
internal fun AddEntryRoute(
    addEntryViewModel: AddEntryViewModel = hiltViewModel(),
    onBackButtonPressed: () -> Unit,
) {
    val state by addEntryViewModel.state.collectAsStateWithLifecycle()
    val navEvents by addEntryViewModel.navigationEvents.collectAsStateWithLifecycle(
        initialValue = AddEntryNavigationEvent.Default,
    )

    when (navEvents) {

        AddEntryNavigationEvent.Default -> { /* do nothing */
        }

        AddEntryNavigationEvent.CloseScreen -> {
            onBackButtonPressed()
        }
    }

    AddEntryScreen(
        addEntryUIState = state,
        onEmotionPressed = { addEntryViewModel.onEvent(AddEntryUIEvent.EmotionSelected(it.toEmotion())) },
        onDiaryEntryTextChanged = {
            addEntryViewModel.onEvent(
                AddEntryUIEvent.DiaryEntryTextChanged(
                    it
                )
            )
        },
        onAddButtonPressed = {
            addEntryViewModel.onEvent(AddEntryUIEvent.AddButtonPressed)
        },
        onCancelButtonPressed = {
            addEntryViewModel.onEvent(AddEntryUIEvent.CancelButtonPressed)
        }
    )
}

@Composable
internal fun AddEntryScreen(
    addEntryUIState: AddEntryUIState,
    onEmotionPressed: (EmotionSymbol) -> Unit,
    onDiaryEntryTextChanged: (String) -> Unit,
    onAddButtonPressed: () -> Unit,
    onCancelButtonPressed: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 32.dp)
        ) {
            EmotionPicker(
                selection = addEntryUIState.selectedEmotion?.toEmotionSymbol(),
                onEmotionPressed = onEmotionPressed,
                noSelectionError = addEntryUIState.emotionNotSelectedError
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.diary_entry_text_title),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextFieldWithCharLimit(
                modifier = Modifier.height(300.dp),
                value = addEntryUIState.diaryEntryText,
                hint = stringResource(id = R.string.diary_entry_text_hint),
                onValueChange = onDiaryEntryTextChanged,
                maxCharLimit = MAX_CHAR_LENGTH_OF_DIARY_ENTRY,
            )
            Spacer(modifier = Modifier.height(32.dp))
            MoodlineButton(
                text = stringResource(id = R.string.add_entry_save_button),
                modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = onAddButtonPressed,
            )
            Spacer(modifier = Modifier.height(8.dp))
            MoodlineOutlinedButton(
                text = stringResource(id = R.string.cancel_entry_save_button),
                modifier = Modifier
                    .width(150.dp)
                    .align(Alignment.CenterHorizontally),
                onClick = onCancelButtonPressed,
            )
        }

        if (addEntryUIState.progress) {
            FullScreenProgressIndicator()
        }
    }
}