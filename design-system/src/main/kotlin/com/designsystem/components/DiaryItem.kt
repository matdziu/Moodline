package com.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.designsystem.theme.MoodlineTheme
import com.designsystem.theme.customColors

@Composable
fun DiaryItem(
    modifier: Modifier = Modifier,
    text: String,
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = modifier.padding(all = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadEmotionImage(modifier = Modifier.size(60.dp))
                Spacer(modifier = modifier.width(16.dp))
                Text(
                    text = "rad",
                    style = MaterialTheme.typography.displayMedium,
                    color = customColors.radEmotionColor,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = text,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Preview
@Composable
private fun DiaryItemPreview() {
    MoodlineTheme {
        DiaryItem(
            text = "This is a preview text. This text will be cut when it does not fit the screen. Lorem ipsum bla bla bla"
        )
    }
}