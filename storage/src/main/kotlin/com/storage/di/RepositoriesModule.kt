package com.storage.di

import com.domain.repositories.DiaryEntriesRepository
import com.storage.repositories.LocalStorageDiaryEntriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class RepositoriesModule {

    @Binds
    @ViewModelScoped
    internal abstract fun bindDiaryEntriesRepository(
        localStorageDiaryEntriesRepository: LocalStorageDiaryEntriesRepository,
    ): DiaryEntriesRepository
}