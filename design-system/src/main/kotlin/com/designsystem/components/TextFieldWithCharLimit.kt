package com.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.R
import com.designsystem.theme.MoodlineTheme

@Composable
fun TextFieldWithCharLimit(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    maxCharLimit: Int,
) {
    val supportingTextTitle = if (value.length <= maxCharLimit) {
        "${value.length} / $maxCharLimit"
    } else {
        "${stringResource(id = R.string.text_field_with_char_limit_supporting_text)} ${value.length} / $maxCharLimit"
    }
    val supportingTextTitleColor = if (value.length <= maxCharLimit) {
        MaterialTheme.colorScheme.onSurface
    } else {
        MaterialTheme.colorScheme.error
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = hint,
                modifier = Modifier.alpha(0.6f),
            )
        },
        supportingText = {
            Text(
                text = supportingTextTitle,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.End,
                color = supportingTextTitleColor
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun TextFieldWithCharLimitPreview() {
    MoodlineTheme {
        TextFieldWithCharLimit(
            value = "",
            hint = "This is a hint",
            onValueChange = {},
            maxCharLimit = 100,
        )
    }
}