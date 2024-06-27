package com.domain.entities

sealed interface Emotion {

    data object Rad : Emotion

    data object Good : Emotion

    data object Meh : Emotion

    data object Bad : Emotion

    data object Awful : Emotion
}