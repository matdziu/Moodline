package com.designsystem.components

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.designsystem.theme.MoodlineTheme

@Composable
fun MoodlineOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = MaterialTheme.colorScheme.inverseSurface
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        colors = ButtonColors(
            contentColor = color,
            containerColor = Color.Transparent,
            disabledContentColor = color.copy(alpha = 0.3f),
            disabledContainerColor = Color.Transparent,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = color,
        )
    ) {
        Text(
            text = text,
            letterSpacing = 1.sp,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun MoodlineOutlinedButtonPreviewLightMode() {
    MoodlineTheme {
        Column {
            MoodlineOutlinedButton(text = "Test Enabled", enabled = true, onClick = { /*TODO*/ })
            MoodlineOutlinedButton(text = "Test Disabled", enabled = false, onClick = { /*TODO*/ })
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MoodlineOutlinedButtonPreviewDarkMode() {
    MoodlineTheme {
        Column {
            MoodlineOutlinedButton(text = "Test Enabled", enabled = true, onClick = { /*TODO*/ })
            MoodlineOutlinedButton(text = "Test Disabled", enabled = false, onClick = { /*TODO*/ })
        }
    }
}