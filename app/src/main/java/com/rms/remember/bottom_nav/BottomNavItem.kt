package com.rms.remember.bottom_nav

import androidx.annotation.DrawableRes
import com.rms.remember.R

sealed class BottomNavItem(
    val title: String,
    @DrawableRes val icon: Int,
    val route: String
) {
    object Tasks : BottomNavItem("Tasks", R.drawable.ic_todo_nav, "tasks")
    object Notes : BottomNavItem("Notes", R.drawable.ic_note_nav, "notes")
}

val items = listOf(
    BottomNavItem.Tasks,
    BottomNavItem.Notes
)

