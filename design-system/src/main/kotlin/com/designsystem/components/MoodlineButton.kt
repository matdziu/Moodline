package com.designsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.designsystem.theme.MoodlineTheme

@Composable
fun MoodlineButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f),
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f),
        )
    ) {
        Text(
            text = text,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MoodlineButtonPreviewLightMode() {
    MoodlineTheme {
        Column {
            MoodlineButton(text = "Test Enabled", enabled = true, onClick = { /*TODO*/ })
            MoodlineButton(text = "Test Disabled", enabled = false, onClick = { /*TODO*/ })
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MoodlineButtonPreviewDarkMode() {
    MoodlineTheme {
        Column {
            MoodlineButton(text = "Test Enabled", enabled = true, onClick = { /*TODO*/ })
            MoodlineButton(text = "Test Disabled", enabled = false, onClick = { /*TODO*/ })
        }
    }
}