package com.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.storage.converters.Converters
import com.storage.daos.DiaryEntryDao
import com.storage.models.DiaryEntryDb

@Database(entities = [DiaryEntryDb::class], version = 1)
@TypeConverters(Converters::class)
internal abstract class MoodlineRoomDatabase : RoomDatabase() {

    abstract fun diaryEntryDao(): DiaryEntryDao
}