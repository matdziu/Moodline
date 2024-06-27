package com.diary.extensions

import com.designsystem.components.EmotionSymbol
import com.domain.entities.Emotion

fun Emotion.toEmotionSymbol(): EmotionSymbol {
    return when (this) {
        Emotion.Awful -> EmotionSymbol.Awful
        Emotion.Bad -> EmotionSymbol.Bad
        Emotion.Good -> EmotionSymbol.Good
        Emotion.Meh -> EmotionSymbol.Meh
        Emotion.Rad -> EmotionSymbol.Rad
    }
}