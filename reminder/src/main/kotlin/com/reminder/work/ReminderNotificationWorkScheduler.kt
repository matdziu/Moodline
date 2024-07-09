package com.reminder.work

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.common.constants.REMINDER_WORK_NAME
import com.reminder.dialog.ReminderPeriodId
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.firstOrNull
import java.time.LocalTime
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject

internal class ReminderNotificationWorkScheduler @Inject constructor(
    @ApplicationContext private val appContext: Context,
) {

    fun schedule(
        reminderPeriodId: ReminderPeriodId,
        time: LocalTime,
    ) {
        val repeatIntervalInDays: Long = when (reminderPeriodId) {
            ReminderPeriodId.EVERY_DAY -> 1
            ReminderPeriodId.EVERY_OTHER_DAY -> 2
            ReminderPeriodId.EVERY_WEEK -> 7
        }

        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, time.hour)
            set(Calendar.MINUTE, time.minute)
        }

        if (target.before(now)) {
            target.add(Calendar.DAY_OF_YEAR, 1)
        }

        val reminderNotificationWorkRequest =
            PeriodicWorkRequestBuilder<ReminderNotificationWorker>(
                repeatIntervalInDays, TimeUnit.DAYS
            )
                .setInitialDelay(
                    target.timeInMillis - System.currentTimeMillis(),
                    TimeUnit.MILLISECONDS
                )
                .build()

        WorkManager
            .getInstance(appContext)
            .enqueueUniquePeriodicWork(
                REMINDER_WORK_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                reminderNotificationWorkRequest
            )
    }

    fun cancel() {
        WorkManager
            .getInstance(appContext)
            .cancelUniqueWork(REMINDER_WORK_NAME)
    }

    suspend fun isScheduled(): Boolean {
        return WorkManager
            .getInstance(appContext)
            .getWorkInfosForUniqueWorkFlow(REMINDER_WORK_NAME)
            .firstOrNull {
                val workInfo = it.firstOrNull()
                workInfo?.state == WorkInfo.State.ENQUEUED || workInfo?.state == WorkInfo.State.RUNNING
            }
            ?.isNotEmpty() ?: false
    }
}