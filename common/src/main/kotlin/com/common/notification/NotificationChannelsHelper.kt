package com.common.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat
import com.common.R
import com.common.constants.DIARY_REMINDER_CHANNEL_ID

object NotificationChannelsHelper {

    fun createAllChannels(context: Context) {
        createNotificationChannel(
            channelId = DIARY_REMINDER_CHANNEL_ID,
            name = context.getString(R.string.diary_reminder_channel_name),
            descriptionText = context.getString(R.string.diary_reminder_channel_description),
            context = context
        )
    }

    private fun createNotificationChannel(
        channelId: String,
        name: String,
        descriptionText: String,
        context: Context
    ) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        ContextCompat.getSystemService(context, NotificationManager::class.java)?.apply {
            createNotificationChannel(channel)
        }
    }
}