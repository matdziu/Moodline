package com.stats

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class StatsUIState(
    val progress: Boolean = false,
    val statsItems: ImmutableList<StatsItem> = persistentListOf(),
)

data class StatsItem(
    val label: String,
    val radCount: Int,
    val goodCount: Int,
    val mehCount: Int,
    val badCount: Int,
    val awfulCount: Int,
)