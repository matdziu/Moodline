package com.reminder.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.components.MoodlineOutlinedButton
import com.designsystem.components.MoodlineTimePicker
import com.designsystem.theme.MoodlineTheme
import com.reminder.R
import com.reminder.dialog.ReminderUIEvent.ReminderDropdownClicked
import com.reminder.dialog.ReminderUIEvent.ReminderDropdownOnDismiss
import com.reminder.dialog.ReminderUIEvent.ReminderDropdownOptionSelected
import kotlinx.collections.immutable.ImmutableList
import java.time.LocalTime

@Composable
fun ReminderPickerDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    onConfirmButtonClicked: () -> Unit = {},
    onDismissButtonClicked: () -> Unit = {},
    containerColor: Color = MaterialTheme.colorScheme.surface,
) {
    val reminderViewModel: ReminderViewModel = hiltViewModel()
    val state by reminderViewModel.state.collectAsStateWithLifecycle()

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        ),
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = modifier
                .width(IntrinsicSize.Max)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = containerColor
                ),
            color = containerColor
        ) {
            ReminderPickerDialogContent(
                reminderUIState = state,
                onReminderDropdownClicked = { reminderViewModel.onEvent(ReminderDropdownClicked) },
                onReminderOptionSelected = {
                    reminderViewModel.onEvent(
                        ReminderDropdownOptionSelected(it)
                    )
                },
                onDropdownDismiss = { reminderViewModel.onEvent(ReminderDropdownOnDismiss) },
                onTimeSelected = { reminderViewModel.onEvent(ReminderUIEvent.TimeSelected(it)) },
                onConfirmButtonClicked = onConfirmButtonClicked,
                onDismissButtonClicked = onDismissButtonClicked,
            )

        }
    }
}

@Composable
private fun ReminderPickerDialogContent(
    reminderUIState: ReminderUIState,
    onReminderDropdownClicked: () -> Unit,
    onReminderOptionSelected: (ReminderDropdownOption) -> Unit,
    onDropdownDismiss: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit,
    onConfirmButtonClicked: () -> Unit,
    onDismissButtonClicked: () -> Unit,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(32.dp)
    ) {
        Text(
            text = stringResource(id = R.string.reminder_title),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        ReminderDropdownMenu(
            options = reminderUIState.dropdownOptions,
            expanded = reminderUIState.expanded,
            currentlySelectedDropdownOption = reminderUIState.selectedDropdownOption,
            onReminderDropdownClicked = onReminderDropdownClicked,
            onReminderOptionSelected = onReminderOptionSelected,
            onDismiss = onDropdownDismiss,
        )

        MoodlineTimePicker(
            selectedTime = reminderUIState.selectedTime,
            onTimeSelected = onTimeSelected,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            MoodlineOutlinedButton(
                text = stringResource(id = R.string.reminder_negative_button),
                onClick = {
                    onDismissButtonClicked()
                }
            )

            Spacer(modifier = Modifier.width(16.dp))

            MoodlineOutlinedButton(
                text = stringResource(id = R.string.reminder_positive_button),
                onClick = {
                    onConfirmButtonClicked()
                }
            )

        }
    }
}

@Composable
private fun ReminderDropdownMenu(
    options: ImmutableList<ReminderDropdownOption>,
    expanded: Boolean,
    currentlySelectedDropdownOption: ReminderDropdownOption,
    onReminderDropdownClicked: () -> Unit,
    onReminderOptionSelected: (ReminderDropdownOption) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable {
                onReminderDropdownClicked()
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Text(
            text = stringResource(id = currentlySelectedDropdownOption.title),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.width(16.dp))

        Icon(
            imageVector = Icons.Default.ArrowDropDown,
            contentDescription = null,
        )

        DropdownMenu(
            expanded = expanded, onDismissRequest = onDismiss
        ) {

            options.forEach {
                DropdownMenuItem(
                    text = { Text(text = stringResource(id = it.title)) },
                    onClick = {
                        onReminderOptionSelected(it)
                        onDismiss()
                    }
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ReminderPickerDialogPreview() {
    MoodlineTheme {
        ReminderPickerDialog()
    }
}