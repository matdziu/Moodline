package com.common.extensions

import com.designsystem.components.EmotionSymbol
import com.domain.entities.Emotion

fun EmotionSymbol.toEmotion(): Emotion {
    return when (this) {
        EmotionSymbol.Awful -> Emotion.Awful
        EmotionSymbol.Bad -> Emotion.Bad
        EmotionSymbol.Good -> Emotion.Good
        EmotionSymbol.Meh -> Emotion.Meh
        EmotionSymbol.Rad -> Emotion.Rad
    }
}

fun Emotion.toEmotionSymbol(): EmotionSymbol {
    return when (this) {
        Emotion.Awful -> EmotionSymbol.Awful
        Emotion.Bad -> EmotionSymbol.Bad
        Emotion.Good -> EmotionSymbol.Good
        Emotion.Meh -> EmotionSymbol.Meh
        Emotion.Rad -> EmotionSymbol.Rad
    }
}