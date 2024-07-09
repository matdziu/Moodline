package com.stats

data class StatsItem(
    val month: Int,
    val year: Int,
    val numberOfRad: Int,
    val numberOfGood: Int,
    val numberOfMeh: Int,
    val numberOfBad: Int,
    val numberOfAwful: Int,
)