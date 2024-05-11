package com.rms.feature.menu.presentation.ui

import com.rms.ui.base.ViewEvent
import com.rms.ui.base.ViewSideEffect
import com.rms.ui.base.ViewState

data class MenuUiState(val menuItems: List<MenuItem>) : ViewState

sealed class MenuUiEffect : ViewSideEffect {

}


sealed class MenuUiEvent : ViewEvent {

}




