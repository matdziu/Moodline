package com.moodline

import android.app.Application
import com.common.notification.NotificationChannelsHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MoodlineApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        NotificationChannelsHelper.createAllChannels(applicationContext)
    }
}