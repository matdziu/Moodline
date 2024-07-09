package com.reminder.work

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.common.constants.ADD_ENTRY_DEEP_LINK
import com.common.constants.APP_URI
import com.common.constants.DIARY_REMINDER_CHANNEL_ID
import com.common.notification.NotificationHelper
import com.reminder.R
import kotlin.random.Random

class ReminderNotificationWorker(
    private val appContext: Context,
    workerParams: WorkerParameters
) : Worker(
    appContext,
    workerParams,
) {

    override fun doWork(): Result {

        val intent = Intent(
            Intent.ACTION_VIEW,
            ADD_ENTRY_DEEP_LINK.toUri(),
        )
        val pendingIntent =
            PendingIntent.getActivity(appContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        NotificationHelper.showNotification(
            DIARY_REMINDER_CHANNEL_ID,
            title = appContext.getString(R.string.diary_reminder_notification_title),
            text = appContext.getString(R.string.diary_reminder_notification_text),
            context = appContext,
            intent = pendingIntent,
            notificationId = Random.nextInt()
        )

        return Result.success()
    }
}