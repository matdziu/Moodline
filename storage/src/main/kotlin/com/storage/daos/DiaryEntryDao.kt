package com.storage.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.storage.models.DiaryEntryDb
import kotlinx.coroutines.flow.Flow

@Dao
internal interface DiaryEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entry: DiaryEntryDb)

    @Query("SELECT * FROM diaryEntry")
    fun getAll(): List<DiaryEntryDb>

    @Query("SELECT * FROM diaryEntry WHERE diaryEntry.id = :entryId")
    suspend fun getSingle(entryId: String): DiaryEntryDb

    @Query("SELECT * FROM diaryEntry")
    fun getAllFlow(): Flow<List<DiaryEntryDb>>

    @Update
    fun updateEntries(vararg entry: DiaryEntryDb)

    @Query("DELETE FROM diaryEntry WHERE diaryEntry.id = :entryId")
    fun deleteEntry(entryId: String)

    @Query("SELECT * FROM diaryEntry WHERE diaryEntry.month = :month AND diaryEntry.year = :year")
    fun getByMonthAndYear(month: Int, year: Int): List<DiaryEntryDb>
}