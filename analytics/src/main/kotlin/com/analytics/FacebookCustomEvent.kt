package com.analytics

import android.os.Bundle

sealed class FacebookCustomEvent(
    val eventName: String,
    val params: Bundle? = null,
) {

    data object ImproveRefLinkClicked : FacebookCustomEvent(IMPROVE_REF_LINK_CLICKED)
}