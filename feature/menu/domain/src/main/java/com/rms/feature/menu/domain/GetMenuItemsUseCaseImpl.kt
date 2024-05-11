package com.rms.feature.menu.domain

import javax.inject.Inject

class GetMenuItemsUseCaseImpl @Inject constructor(): GetMenuItemsUseCase {

    override fun getMenuItems(): List<MenuItemEntity> {
        return MenuItemEntity.values().asList()
    }
}