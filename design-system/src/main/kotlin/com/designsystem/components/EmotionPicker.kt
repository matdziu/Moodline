package com.designsystem.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.designsystem.R
import com.designsystem.theme.MoodlineTheme
import com.designsystem.theme.customColors

@Composable
fun EmotionPicker(
    modifier: Modifier = Modifier,
    selection: EmotionSymbol?,
    onEmotionPressed: (EmotionSymbol) -> Unit,
    noSelectionError: Boolean = false,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.emotion_picker_title),
            textAlign = TextAlign.Center
        )
        if (noSelectionError) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.emotion_picker_no_selection_error),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.wrapContentWidth(),
        ) {
            RadEmotionWithSelector(
                onEmotionPressed = onEmotionPressed,
                selection = selection,
            )
            GoodEmotionWithSelector(
                onEmotionPressed = onEmotionPressed,
                selection = selection,
            )
            MehEmotionWithSelector(
                onEmotionPressed = onEmotionPressed,
                selection = selection,
            )
            BadEmotionWithSelector(
                onEmotionPressed = onEmotionPressed,
                selection = selection,
            )
            AwfulEmotionWithSelector(
                onEmotionPressed = onEmotionPressed,
                selection = selection,
            )
        }
    }
}

@Composable
private fun RadEmotionWithSelector(
    onEmotionPressed: (EmotionSymbol) -> Unit,
    selection: EmotionSymbol?,
) {
    EmotionWithSelector(
        emotionSymbol = EmotionSymbol.Rad,
        onEmotionPressed = onEmotionPressed,
        selected = selection == EmotionSymbol.Rad,
    )
}

@Composable
private fun GoodEmotionWithSelector(
    onEmotionPressed: (EmotionSymbol) -> Unit,
    selection: EmotionSymbol?,
) {
    EmotionWithSelector(
        emotionSymbol = EmotionSymbol.Good,
        onEmotionPressed = onEmotionPressed,
        selected = selection == EmotionSymbol.Good,
    )
}

@Composable
private fun MehEmotionWithSelector(
    onEmotionPressed: (EmotionSymbol) -> Unit,
    selection: EmotionSymbol?,
) {
    EmotionWithSelector(
        emotionSymbol = EmotionSymbol.Meh,
        onEmotionPressed = onEmotionPressed,
        selected = selection == EmotionSymbol.Meh,
    )
}

@Composable
private fun BadEmotionWithSelector(
    onEmotionPressed: (EmotionSymbol) -> Unit,
    selection: EmotionSymbol?,
) {
    EmotionWithSelector(
        emotionSymbol = EmotionSymbol.Bad,
        onEmotionPressed = onEmotionPressed,
        selected = selection == EmotionSymbol.Bad,
    )
}

@Composable
private fun AwfulEmotionWithSelector(
    onEmotionPressed: (EmotionSymbol) -> Unit,
    selection: EmotionSymbol?,
) {
    EmotionWithSelector(
        emotionSymbol = EmotionSymbol.Awful,
        onEmotionPressed = onEmotionPressed,
        selected = selection == EmotionSymbol.Awful,
    )
}

@Composable
private fun EmotionWithSelector(
    emotionSymbol: EmotionSymbol,
    onEmotionPressed: (EmotionSymbol) -> Unit,
    selected: Boolean,
) {
    val emotionImageSize = 55.dp
    val emotionSelectorSize = emotionImageSize + 16.dp
    val emotionColor = when (emotionSymbol) {
        EmotionSymbol.Awful -> customColors.awfulEmotionColor
        EmotionSymbol.Bad -> customColors.badEmotionColor
        EmotionSymbol.Good -> customColors.goodEmotionColor
        EmotionSymbol.Meh -> customColors.mehEmotionColor
        EmotionSymbol.Rad -> customColors.radEmotionColor
    }
    val emotionText = stringResource(id =when (emotionSymbol) {
        EmotionSymbol.Awful -> R.string.emotion_symbol_awful_text
        EmotionSymbol.Bad -> R.string.emotion_symbol_bad_text
        EmotionSymbol.Good -> R.string.emotion_symbol_good_text
        EmotionSymbol.Meh -> R.string.emotion_symbol_meh_text
        EmotionSymbol.Rad -> R.string.emotion_symbol_rad_text
    })

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(emotionSelectorSize)
                .clip(CircleShape)
                .clickable(enabled = !selected) { onEmotionPressed(emotionSymbol) },
        ) {
            when (emotionSymbol) {
                EmotionSymbol.Awful -> AwfulEmotionImage(modifier = Modifier.size(emotionImageSize))
                EmotionSymbol.Bad -> BadEmotionImage(modifier = Modifier.size(emotionImageSize))
                EmotionSymbol.Good -> GoodEmotionImage(modifier = Modifier.size(emotionImageSize))
                EmotionSymbol.Meh -> MehEmotionImage(modifier = Modifier.size(emotionImageSize))
                EmotionSymbol.Rad -> RadEmotionImage(modifier = Modifier.size(emotionImageSize))
            }

            if (selected) {
                OvalSelector(
                    modifier = Modifier.size(emotionSelectorSize),
                    color = emotionColor,
                )
            }
        }

        Text(
            modifier = Modifier.width(emotionImageSize),
            textAlign = TextAlign.Center,
            text = emotionText,
            color = emotionColor,
        )
    }
}

@Composable
private fun OvalSelector(
    modifier: Modifier = Modifier,
    color: Color
) {
    Canvas(modifier = modifier.padding(2.dp)) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val radiusX = size.width / 2
        val radiusY = size.height / 2

        drawOval(
            color = color,
            topLeft = Offset(centerX - radiusX, centerY - radiusY),
            size = Size(radiusX * 2, radiusY * 2),
            style = Stroke(width = 2.dp.toPx())
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmotionPickerPreview() {
    MoodlineTheme {
        EmotionPicker(
            selection = EmotionSymbol.Good,
            onEmotionPressed = {},
        )
    }
}