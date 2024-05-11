package com.rms.feature.menu.presentation.ui.mapper

import com.ramees.util.MapTo
import com.rms.feature.menu.domain.MenuItemEntity
import com.rms.feature.menu.presentation.ui.MenuItem
import javax.inject.Inject

class MenuItemMapper @Inject constructor() : MapTo<MenuItemEntity, MenuItem> {

    override fun mapTo(item: MenuItemEntity): MenuItem = when(item){
        MenuItemEntity.TASK_HISTORY -> MenuItem("Task History")
        MenuItemEntity.PRIVATE -> MenuItem("Private")
    }
}