package com.rms.feature.menu.presentation.ui

import com.rms.ui.base.BaseViewModel
import com.rms.ui.base.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor() : BaseViewModel<ViewEvent, MenuUiState, MenuUiEffect>() {

    override fun setInitialState(): MenuUiState = MenuUiState(emptyList())

    override fun handleEvents(event: ViewEvent) {

    }


}