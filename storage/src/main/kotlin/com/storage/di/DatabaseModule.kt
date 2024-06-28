package com.storage.di

import android.content.Context
import androidx.room.Room
import com.storage.MoodlineRoomDatabase
import com.storage.daos.DiaryEntryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideRoomDatabase(
        @ApplicationContext applicationContext: Context,
    ): MoodlineRoomDatabase {
        return Room.databaseBuilder(
            applicationContext,
            MoodlineRoomDatabase::class.java, "moodline-database"
        ).build()
    }

    @Provides
    @Singleton
    internal fun provideDiaryEntryDao(moodlineRoomDatabase: MoodlineRoomDatabase): DiaryEntryDao {
        return moodlineRoomDatabase.diaryEntryDao()
    }
}