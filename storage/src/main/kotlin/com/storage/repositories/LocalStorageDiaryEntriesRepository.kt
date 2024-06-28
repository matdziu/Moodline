package com.storage.repositories

import com.common.coroutines.CoroutineDispatchersProvider
import com.domain.entities.DiaryEntry
import com.domain.repositories.DiaryEntriesRepository
import com.storage.daos.DiaryEntryDao
import com.storage.extensions.toDiaryEntry
import com.storage.extensions.toDiaryEntryDb
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocalStorageDiaryEntriesRepository @Inject constructor(
    private val diaryEntryDao: DiaryEntryDao,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : DiaryEntriesRepository {

    override suspend fun getAll(): List<DiaryEntry> =
        withContext(coroutineDispatchersProvider.io()) {
            diaryEntryDao.getAll().map { it.toDiaryEntry() }
        }

    override suspend fun add(entry: DiaryEntry) = withContext(coroutineDispatchersProvider.io()) {
        diaryEntryDao.insert(entry.toDiaryEntryDb())
    }
}