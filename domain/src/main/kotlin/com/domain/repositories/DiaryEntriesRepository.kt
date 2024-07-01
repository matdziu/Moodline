package com.domain.repositories

import com.domain.entities.DiaryEntry
import kotlinx.coroutines.flow.Flow

interface DiaryEntriesRepository {

    suspend fun getAll(): List<DiaryEntry>

    suspend fun add(entry: DiaryEntry)

    fun getAllFlow(): Flow<List<DiaryEntry>>
}