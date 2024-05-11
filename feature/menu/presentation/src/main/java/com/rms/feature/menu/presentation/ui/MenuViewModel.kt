package com.rms.feature.menu.presentation.ui

import com.ramees.util.mapListTo
import com.rms.feature.menu.domain.GetMenuItemsUseCase
import com.rms.feature.menu.presentation.ui.mapper.MenuItemMapper
import com.rms.ui.base.BaseViewModel
import com.rms.ui.base.ViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val getMenuUseCase: GetMenuItemsUseCase,
    private val menuItemMapper: MenuItemMapper
) : BaseViewModel<ViewEvent, MenuUiState, MenuUiEffect>() {

    init {
        setState { copy(menuItems = getMenuUseCase.getMenuItems().mapListTo(menuItemMapper)) }
    }

    override fun setInitialState(): MenuUiState = MenuUiState(emptyList())

    override fun handleEvents(event: ViewEvent) {

    }
}