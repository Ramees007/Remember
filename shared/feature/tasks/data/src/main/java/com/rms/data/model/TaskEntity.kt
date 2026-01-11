package com.rms.data.model

data class TaskEntity(
    val id: Long = 0,
    val task: String,
    val date: String?,
    val isDone: Boolean,
    val doneDate: String? = null
)
