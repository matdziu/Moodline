package com.designsystem.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun TestComponent() {
    Column {
        Text(text = "1")
        Text(text = "2")
    }
}