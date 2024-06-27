package com.designsystem.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.designsystem.theme.MoodlineTheme

@Composable
fun FullScreenProgressIndicator(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.6f)
    ) {
        Box(modifier = modifier.size(32.dp)) {
            CircularProgressIndicator(
                modifier = modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
@Preview
private fun FullScreenProgressIndicatorPreview() {
    MoodlineTheme {
        FullScreenProgressIndicator()
    }
}