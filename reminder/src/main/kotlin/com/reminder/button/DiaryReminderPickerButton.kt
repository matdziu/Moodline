package com.reminder.button

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.designsystem.components.MoodlineOutlinedButton
import com.designsystem.theme.MoodlineTheme
import com.reminder.R

@Composable
fun DiaryReminderPickerButton(
    modifier: Modifier = Modifier,
) {
    val buttonViewModel: DiaryReminderPickerButtonViewModel = hiltViewModel()
    val state by buttonViewModel.state.collectAsStateWithLifecycle()

    if (state.showToast) {
        buttonViewModel.onEvent(DiaryReminderPickerButtonUIEvent.ToastDisplayed)
        val context = LocalContext.current
        Toast.makeText(
            context,
            state.toastInfoRes ?: R.string.diary_reminder_picker_cancelled_toast_info,
            Toast.LENGTH_SHORT,
        ).show()
    }

    MoodlineOutlinedButton(
        modifier = modifier,
        text = stringResource(id = state.buttonTitleRes),
        onClick = {
            buttonViewModel.onEvent(DiaryReminderPickerButtonUIEvent.ButtonPressed)
        },
        color = if (state.isReminderSet) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.error
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun DiaryReminderPickerButtonPreviewLightMode() {
    MoodlineTheme {
        DiaryReminderPickerButton()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DiaryReminderPickerButtonPreviewDarkMode() {
    MoodlineTheme {
        DiaryReminderPickerButton()
    }
}