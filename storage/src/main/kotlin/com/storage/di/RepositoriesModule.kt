package com.storage.di

import com.domain.repositories.DiaryEntriesRepository
import com.storage.repositories.LocalStorageDiaryEntriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoriesModule {

    @Binds
    internal abstract fun bindDiaryEntriesRepository(
        localStorageDiaryEntriesRepository: LocalStorageDiaryEntriesRepository,
    ): DiaryEntriesRepository
}