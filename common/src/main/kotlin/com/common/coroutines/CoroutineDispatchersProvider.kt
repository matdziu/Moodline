package com.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatchersProvider {

    fun main(): CoroutineDispatcher

    fun io(): CoroutineDispatcher
}