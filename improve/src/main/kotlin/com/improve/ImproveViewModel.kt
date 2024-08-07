package com.improve

import androidx.lifecycle.ViewModel
import com.analytics.FacebookCustomEvent
import com.analytics.FacebookSdkWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImproveViewModel @Inject constructor(
    private val facebookSdk: FacebookSdkWrapper,
) : ViewModel() {

    fun trackRefLinkClick() {
        facebookSdk.logCustomEvent(FacebookCustomEvent.ImproveRefLinkClicked)
    }
}