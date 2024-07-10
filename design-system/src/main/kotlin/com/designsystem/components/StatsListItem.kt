package com.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.designsystem.theme.MoodlineTheme

@Composable
fun StatsListItem(
    modifier: Modifier = Modifier,
    label: String,
    radCount: Int,
    goodCount: Int,
    mehCount: Int,
    badCount: Int,
    awfulCount: Int
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .height(300.dp),
        colors = CardDefaults.elevatedCardColors().copy(
            containerColor = MaterialTheme.colorScheme.onPrimary,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )

            val maxHeightPx = 160f
            val maxEmotionsCount = listOf(radCount, goodCount, mehCount, badCount, awfulCount).max()
            val pxForOneEmotionCount = maxHeightPx / maxEmotionsCount
            val radHeightPx = pxForOneEmotionCount * radCount
            val goodHeightPx = pxForOneEmotionCount * goodCount
            val mehHeightPx = pxForOneEmotionCount * mehCount
            val badHeightPx = pxForOneEmotionCount * badCount
            val awfulHeightPx = pxForOneEmotionCount * awfulCount

            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.Bottom,
            ) {
                EmotionStatsColumn(
                    emotionSymbol = EmotionSymbol.Rad,
                    emotionCount = radCount,
                    modifier = Modifier.height(radHeightPx.dp)
                )
                EmotionStatsColumn(
                    emotionSymbol = EmotionSymbol.Good,
                    emotionCount = goodCount,
                    modifier = Modifier.height(goodHeightPx.dp),
                )
                EmotionStatsColumn(
                    emotionSymbol = EmotionSymbol.Meh,
                    emotionCount = mehCount,
                    modifier = Modifier.height(mehHeightPx.dp),
                )
                EmotionStatsColumn(
                    emotionSymbol = EmotionSymbol.Bad,
                    emotionCount = badCount,
                    modifier = Modifier.height(badHeightPx.dp),
                )
                EmotionStatsColumn(
                    emotionSymbol = EmotionSymbol.Awful,
                    emotionCount = awfulCount,
                    modifier = Modifier.height(awfulHeightPx.dp),
                )
            }
        }
    }
}

@Composable
private fun EmotionStatsColumn(
    emotionSymbol: EmotionSymbol,
    emotionCount: Int,
    modifier: Modifier = Modifier,
) {
    val emotionColor = emotionSymbol.toColor()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = modifier
                .width(24.dp)
                .clip(RoundedCornerShape(corner = CornerSize(8.dp)))
                .background(emotionColor),
        )

        Spacer(modifier = Modifier.height(8.dp))

        val emotionImageModifier = Modifier.size(24.dp)
        when (emotionSymbol) {
            EmotionSymbol.Awful -> AwfulEmotionImage(emotionImageModifier)
            EmotionSymbol.Bad -> BadEmotionImage(emotionImageModifier)
            EmotionSymbol.Good -> GoodEmotionImage(emotionImageModifier)
            EmotionSymbol.Meh -> MehEmotionImage(emotionImageModifier)
            EmotionSymbol.Rad -> RadEmotionImage(emotionImageModifier)
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = emotionCount.toString(),
            color = emotionColor,
            fontWeight = FontWeight.ExtraBold,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StatsListItemPreview() {
    MoodlineTheme {
        StatsListItem(
            label = "January, 2024",
            radCount = 200,
            goodCount = 100,
            mehCount = 100,
            badCount = 100,
            awfulCount = 100
        )
    }
}