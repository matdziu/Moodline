package com.analytics

import android.content.Context
import com.facebook.appevents.AppEventsLogger
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FacebookSdkWrapper @Inject constructor(
    @ApplicationContext appContext: Context
) {

    private val appEventsLogger = AppEventsLogger.newLogger(appContext)

    fun logCustomEvent(event: FacebookCustomEvent) {
        appEventsLogger.logEvent(event.eventName, event.params)
    }
}