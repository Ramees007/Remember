package com.rms.remember.bottom_nav

import androidx.annotation.DrawableRes
import com.rms.feature.menu.presentation.ui.MENU_GRAPH_ROUTE
import com.rms.notes.ui.NOTES_GRAPH_ROUTE
import com.rms.remember.R
import com.rms.tasks.ui.TASKS_GRAPH_ROUTE

sealed class BottomNavItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
) {
    object Tasks : BottomNavItem("Tasks", R.drawable.ic_todo_nav, TASKS_GRAPH_ROUTE)

    object Notes : BottomNavItem("Notes", R.drawable.ic_note_nav, NOTES_GRAPH_ROUTE)

    object Menu: BottomNavItem("Menu", R.drawable.ic_menu, MENU_GRAPH_ROUTE)
}

val items = listOf(
    BottomNavItem.Tasks,
    BottomNavItem.Notes,
    BottomNavItem.Menu
)

