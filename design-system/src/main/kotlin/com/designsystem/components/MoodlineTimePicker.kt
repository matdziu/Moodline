package com.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.common.constants.COMMON_TIME_FORMAT
import com.designsystem.R
import com.designsystem.theme.MoodlineTheme
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodlineTimePicker(
    modifier: Modifier = Modifier,
    selectedTime: LocalTime,
    onTimeSelected: (LocalTime) -> Unit,
) {
    val timeFormatter = DateTimeFormatter.ofPattern(COMMON_TIME_FORMAT)
    var showTimePicker by rememberSaveable { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = selectedTime.format(timeFormatter),
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.5f)
        )
        Spacer(modifier = Modifier.width(8.dp))
        MoodlineButton(
            modifier = Modifier.weight(0.5f),
            text = stringResource(id = R.string.time_picker_button_text),
            onClick = { showTimePicker = true })
    }

    if (showTimePicker) {
        val timePickerState = rememberTimePickerState(
            initialHour = selectedTime.hour,
            initialMinute = selectedTime.minute,
            is24Hour = true,
        )
        DateTimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            confirmButton = {
                MoodlineOutlinedButton(
                    text = stringResource(id = R.string.time_picker_button_positive),
                    onClick = {
                        showTimePicker = false
                        onTimeSelected(LocalTime.of(timePickerState.hour, timePickerState.minute))
                    }
                )
            },
            dismissButton = {
                MoodlineOutlinedButton(
                    text = stringResource(id = R.string.time_picker_button_dismiss),
                    onClick = {
                        showTimePicker = false
                    }
                )
            }
        ) {
            TimePicker(state = timePickerState)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodlineTimePickerPreview() {
    MoodlineTheme {
        MoodlineTimePicker(
            selectedTime = LocalTime.now(),
            onTimeSelected = {},
        )
    }
}