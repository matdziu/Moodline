package com.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.designsystem.theme.MoodlineTheme

@Composable
internal fun StatsRoute() {
    StatsScreen()
}

@Composable
internal fun StatsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(text = "STATS")
    }
}

@Composable
@Preview(showBackground = true)
private fun StatsScreenPreview() {
    MoodlineTheme {
        StatsScreen()
    }
}