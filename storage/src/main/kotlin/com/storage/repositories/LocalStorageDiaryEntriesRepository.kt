package com.storage.repositories

import com.common.coroutines.CoroutineDispatchersProvider
import com.domain.entities.DiaryEntry
import com.domain.repositories.DiaryEntriesRepository
import com.storage.daos.DiaryEntryDao
import com.storage.extensions.toDiaryEntry
import com.storage.extensions.toDiaryEntryDb
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class LocalStorageDiaryEntriesRepository @Inject constructor(
    private val diaryEntryDao: DiaryEntryDao,
    private val coroutineDispatchersProvider: CoroutineDispatchersProvider
) : DiaryEntriesRepository {

    override suspend fun getAll(): List<DiaryEntry> =
        withContext(coroutineDispatchersProvider.io()) {
            diaryEntryDao.getAll()
                .sortedByDescending { entry -> entry.createdAt }
                .map { it.toDiaryEntry() }
        }

    override suspend fun getSingle(entryId: String): DiaryEntry =
        withContext(coroutineDispatchersProvider.io()) {
            diaryEntryDao.getSingle(entryId).toDiaryEntry()
        }

    override suspend fun add(entry: DiaryEntry) = withContext(coroutineDispatchersProvider.io()) {
        diaryEntryDao.insert(entry.toDiaryEntryDb())
    }

    override fun getAllFlow(): Flow<List<DiaryEntry>> {
        return diaryEntryDao.getAllFlow().map { entries ->
            entries
                .sortedByDescending { entry -> entry.createdAt }
                .map { it.toDiaryEntry() }
        }
    }

    override suspend fun deleteEntry(entryId: String) =
        withContext(coroutineDispatchersProvider.io()) {
            diaryEntryDao.deleteEntry(entryId)
        }

    override suspend fun updateEntry(diaryEntry: DiaryEntry) =
        withContext(coroutineDispatchersProvider.io()) {
            diaryEntryDao.updateEntries(diaryEntry.toDiaryEntryDb())
        }

    override suspend fun getByMonthAndYearFlow(month: Int, year: Int): List<DiaryEntry> {
        return withContext(coroutineDispatchersProvider.io()) {
            diaryEntryDao.getByMonthAndYear(month = month, year = year).map {
                it.toDiaryEntry()
            }
        }
    }
}