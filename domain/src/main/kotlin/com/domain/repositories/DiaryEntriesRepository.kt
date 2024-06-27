package com.domain.repositories

import com.domain.entities.DiaryEntry

interface DiaryEntriesRepository {

    suspend fun getAll(): List<DiaryEntry>

    suspend fun add(entry: DiaryEntry)
}