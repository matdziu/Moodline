package com.designsystem.components

sealed interface EmotionSymbol {

    data object Rad : EmotionSymbol

    data object Good : EmotionSymbol

    data object Meh : EmotionSymbol

    data object Bad : EmotionSymbol

    data object Awful : EmotionSymbol
}