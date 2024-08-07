package com.moodline

import androidx.lifecycle.SavedStateHandle
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class BasicMainViewModelProvider {

    val savedStateHandle: SavedStateHandle = mock {
        on<String?> { it[SELECTED_NAV_ITEM_ID_KEY] } doReturn null
    }

    fun provide(): MainViewModel {
        return MainViewModel(
            savedStateHandle = savedStateHandle,
            facebookSdkWrapper = mock(),
        )
    }
}