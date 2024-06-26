package com.designsystem.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.designsystem.R
import com.designsystem.theme.MoodlineTheme
import com.designsystem.theme.customColors

@Composable
fun RadEmotionImage(
    modifier: Modifier = Modifier
) {
    EmotionImage(
        emotionSymbol = EmotionSymbol.Rad,
        tint = customColors.radEmotionColor,
        modifier = modifier,
    )
}

@Composable
fun GoodEmotionImage(
    modifier: Modifier = Modifier
) {
    EmotionImage(
        emotionSymbol = EmotionSymbol.Good,
        tint = customColors.goodEmotionColor,
        modifier = modifier,
    )
}

@Composable
fun MehEmotionImage(
    modifier: Modifier = Modifier
) {
    EmotionImage(
        emotionSymbol = EmotionSymbol.Meh,
        tint = customColors.mehEmotionColor,
        modifier = modifier,
    )
}

@Composable
fun BadEmotionImage(
    modifier: Modifier = Modifier
) {
    EmotionImage(
        emotionSymbol = EmotionSymbol.Bad,
        tint = customColors.badEmotionColor,
        modifier = modifier,
    )
}

@Composable
fun AwfulEmotionImage(
    modifier: Modifier = Modifier
) {
    EmotionImage(
        emotionSymbol = EmotionSymbol.Awful,
        tint = customColors.awfulEmotionColor,
        modifier = modifier,
    )
}

@Composable
fun EmotionImage(
    emotionSymbol: EmotionSymbol,
    tint: Color,
    modifier: Modifier = Modifier,
) {
    val painter = painterResource(
        id = when (emotionSymbol) {
            is EmotionSymbol.Awful -> R.drawable.ic_awful
            is EmotionSymbol.Bad -> R.drawable.ic_bad
            is EmotionSymbol.Good -> R.drawable.ic_good
            is EmotionSymbol.Meh -> R.drawable.ic_meh
            is EmotionSymbol.Rad -> R.drawable.ic_rad
        }
    )
    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = null,
        colorFilter = ColorFilter.tint(color = tint, blendMode = BlendMode.Modulate)
    )
}

@Preview(
    widthDp = 600,
    heightDp = 200,
    showBackground = true,
)
@Composable
private fun EmotionImagePreview() {
    MoodlineTheme {
        Row(
            modifier = Modifier
                .padding(all = 16.dp)
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            RadEmotionImage(modifier = Modifier.size(100.dp))
            GoodEmotionImage(modifier = Modifier.size(100.dp))
            MehEmotionImage(modifier = Modifier.size(100.dp))
            BadEmotionImage(modifier = Modifier.size(100.dp))
            AwfulEmotionImage(modifier = Modifier.size(100.dp))
        }
    }
}