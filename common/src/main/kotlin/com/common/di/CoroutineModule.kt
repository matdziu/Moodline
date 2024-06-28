package com.common.di

import com.common.coroutines.CoroutineDispatchersProvider
import com.common.coroutines.DefaultCoroutineDispatchersProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineModule {

    @Binds
    @Singleton
    internal abstract fun bindCoroutineDispatchersProvider(
        defaultCoroutineDispatchersProvider: DefaultCoroutineDispatchersProvider
    ): CoroutineDispatchersProvider
}