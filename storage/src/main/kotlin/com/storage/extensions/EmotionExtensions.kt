package com.storage.extensions

import com.domain.entities.Emotion
import com.storage.models.EmotionDb

internal fun Emotion.toEmotionDb(): EmotionDb {
    return when(this) {
        Emotion.Awful -> EmotionDb.AWFUL
        Emotion.Bad -> EmotionDb.BAD
        Emotion.Good -> EmotionDb.GOOD
        Emotion.Meh -> EmotionDb.MEH
        Emotion.Rad -> EmotionDb.RAD
    }
}

internal fun EmotionDb.toEmotion(): Emotion {
    return when(this) {
        EmotionDb.RAD -> Emotion.Rad
        EmotionDb.GOOD -> Emotion.Good
        EmotionDb.MEH -> Emotion.Meh
        EmotionDb.BAD -> Emotion.Bad
        EmotionDb.AWFUL -> Emotion.Awful
    }
}