package com.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
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
import com.common.constants.COMMON_DATE_FORMAT
import com.common.extensions.toMillis
import com.designsystem.R
import com.designsystem.theme.MoodlineTheme
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoodlineDatePicker(
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit,
) {
    val now = LocalDateTime.now()
    val dateTimeFormatter = DateTimeFormatter.ofPattern(COMMON_DATE_FORMAT)
    var showDatePicker by rememberSaveable { mutableStateOf(false) }
    var formattedDate by rememberSaveable { mutableStateOf(now.format(dateTimeFormatter)) }
    var selectedDate by rememberSaveable { mutableStateOf(now) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        Text(
            text = formattedDate,
            textAlign = TextAlign.Center,
            modifier = Modifier.weight(0.5f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        MoodlineButton(
            modifier = Modifier.weight(0.5f),
            text = stringResource(id = R.string.date_picker_button_text),
            onClick = { showDatePicker = true })
    }

    if (showDatePicker) {
        val datePickerState = rememberDatePickerState(
            initialSelectedDateMillis = selectedDate.toMillis()
        )
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                MoodlineOutlinedButton(
                    text = stringResource(id = R.string.date_picker_button_positive),
                    onClick = {
                        showDatePicker = false
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            selectedDate = Instant
                                .ofEpochMilli(selectedDateMillis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDateTime()
                            formattedDate = selectedDate.format(dateTimeFormatter)
                            onDateSelected(selectedDate.toLocalDate())
                        }
                    }
                )
            },
            dismissButton = {
                MoodlineOutlinedButton(
                    text = stringResource(id = R.string.date_picker_button_dismiss),
                    onClick = {
                        showDatePicker = false
                    }
                )
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MoodlineDatePickerPreview() {
    MoodlineTheme {
        MoodlineDatePicker(
            onDateSelected = {},
        )
    }
}