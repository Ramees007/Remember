package com.rms.data


import com.rms.db.model.TaskDbItem

data class TaskItem(val id: Long, val task: String, val date: String, val isDone: Boolean)

fun TaskDbItem.toPresentation() = TaskItem(uid, task, date.orEmpty(), isDone)

