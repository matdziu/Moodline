package com.stats

internal sealed interface StatsUIEvent {

    data object Initialize : StatsUIEvent

    data object OnDispose : StatsUIEvent
}