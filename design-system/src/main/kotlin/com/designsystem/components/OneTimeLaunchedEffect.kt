package com.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope

@Composable
fun OneTimeLaunchedEffect(
    block: suspend CoroutineScope.() -> Unit,
) {
    // this LaunchedEffect is not re-launched on configuration changes
    var alreadyLaunched: Boolean by rememberSaveable {
        mutableStateOf(false)
    }

    if (!alreadyLaunched) {
        alreadyLaunched = true
        LaunchedEffect(Unit, block)
    }
}