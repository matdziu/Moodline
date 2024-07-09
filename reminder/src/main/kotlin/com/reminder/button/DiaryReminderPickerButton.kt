package com.reminder.button

import android.content.Context
import android.content.res.Configuration
import android.os.Build
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.reminder.R
import com.reminder.button.DiaryReminderPickerButtonUIEvent.ButtonPressed
import com.reminder.button.DiaryReminderPickerButtonUIEvent.DialogDismissed
import com.reminder.button.DiaryReminderPickerButtonUIEvent.ReminderSet
import com.reminder.button.DiaryReminderPickerButtonUIEvent.ToastDisplayed
import com.reminder.dialog.ReminderPickerDialog

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun DiaryReminderPickerButton(
    modifier: Modifier = Modifier,
) {
    val buttonViewModel: DiaryReminderPickerButtonViewModel = hiltViewModel()
    val state by buttonViewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    if (state.showToast) {
        buttonViewModel.onEvent(ToastDisplayed)
        Toast.makeText(
            context,
            state.toastInfoRes ?: R.string.diary_reminder_picker_cancelled_toast_info,
            Toast.LENGTH_SHORT,
        ).show()
    }

    val notificationsPermissionsState: PermissionState? =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            rememberPermissionState(
                android.Manifest.permission.POST_NOTIFICATIONS
            )
        } else {
            null
        }

    MoodlineOutlinedButton(
        modifier = modifier,
        text = stringResource(id = state.buttonTitleRes),
        onClick = {
            onButtonClicked(
                permissionState = notificationsPermissionsState,
                context = context,
                buttonViewModel = buttonViewModel,
            )
        },
        color = if (state.isReminderSet) MaterialTheme.colorScheme.inverseSurface else MaterialTheme.colorScheme.error
    )

    if (state.showDialog) {
        ReminderPickerDialog(
            onDismissRequest = { buttonViewModel.onEvent(DialogDismissed) },
            onConfirmButtonClicked = { buttonViewModel.onEvent(ReminderSet) },
            onDismissButtonClicked = { buttonViewModel.onEvent(DialogDismissed) }
        )
    }
}

@OptIn(ExperimentalPermissionsApi::class)
private fun onButtonClicked(
    permissionState: PermissionState?,
    context: Context,
    buttonViewModel: DiaryReminderPickerButtonViewModel,
) {
    if (permissionState?.status?.isGranted != false) {
        buttonViewModel.onEvent(ButtonPressed)
    } else {
        Toast.makeText(
            context,
            R.string.diary_reminder_permission_denied_rationale,
            Toast.LENGTH_SHORT,
        ).show()
        permissionState.launchPermissionRequest()
    }
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