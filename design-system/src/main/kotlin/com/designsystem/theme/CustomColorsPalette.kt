package com.designsystem.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColorsPalette(
    val radEmotionColor: Color = Color.Unspecified,
    val goodEmotionColor: Color = Color.Unspecified,
    val mehEmotionColor: Color = Color.Unspecified,
    val badEmotionColor: Color = Color.Unspecified,
    val awfulEmotionColor: Color = Color.Unspecified,
)

val LocalCustomColorsPalette: ProvidableCompositionLocal<CustomColorsPalette> =
    staticCompositionLocalOf { error("CustomColorsPalette has to be provided through CompositionLocal") }
val customColors: CustomColorsPalette
    @Composable
    @ReadOnlyComposable
    get() = LocalCustomColorsPalette.current