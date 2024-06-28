package com.common.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class DefaultCoroutineDispatchersProvider @Inject constructor() : CoroutineDispatchersProvider {

    override fun main(): CoroutineDispatcher {
        return Dispatchers.Main.immediate
    }

    override fun io(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}