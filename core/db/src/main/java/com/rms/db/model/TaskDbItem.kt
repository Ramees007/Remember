package com.rms.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskDbItem(
    @PrimaryKey(autoGenerate = true) val uid: Long = 0,
    val task: String,
    val date: String?,
    val isDone: Boolean,
    val doneDate: String? = null
)
