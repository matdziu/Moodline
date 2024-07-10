package com.designsystem.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.designsystem.theme.customColors

sealed interface EmotionSymbol {

    data object Rad : EmotionSymbol

    data object Good : EmotionSymbol

    data object Meh : EmotionSymbol

    data object Bad : EmotionSymbol

    data object Awful : EmotionSymbol
}

@Composable
fun EmotionSymbol.toColor(): Color {
    return when (this) {
        EmotionSymbol.Awful -> customColors.awfulEmotionColor
        EmotionSymbol.Bad -> customColors.badEmotionColor
        EmotionSymbol.Good -> customColors.goodEmotionColor
        EmotionSymbol.Meh -> customColors.mehEmotionColor
        EmotionSymbol.Rad -> customColors.radEmotionColor
    }
}