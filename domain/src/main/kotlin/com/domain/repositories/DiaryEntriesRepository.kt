package com.domain.repositories

import com.domain.entities.DiaryEntry
import kotlinx.coroutines.flow.Flow

interface DiaryEntriesRepository {

    suspend fun getAll(): List<DiaryEntry>

    suspend fun getSingle(entryId: String): DiaryEntry

    suspend fun add(entry: DiaryEntry)

    fun getAllFlow(): Flow<List<DiaryEntry>>

    suspend fun deleteEntry(entryId: String)

    suspend fun updateEntry(diaryEntry: DiaryEntry)
}