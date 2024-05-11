package com.rms.feature.menu.domain

interface GetMenuItemsUseCase {

    fun getMenuItems(): List<MenuItemEntity>
}