package com.storage.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.storage.models.DiaryEntryDb
import kotlinx.coroutines.flow.Flow

@Dao
internal interface DiaryEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entry: DiaryEntryDb)

    @Query("SELECT * FROM diaryEntries")
    fun getAll(): List<DiaryEntryDb>

    @Query("SELECT * FROM diaryEntries")
    fun getAllFlow(): Flow<List<DiaryEntryDb>>
}