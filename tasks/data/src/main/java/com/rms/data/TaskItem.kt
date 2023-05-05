package com.rms.data


import com.rms.db.model.TaskDbItem
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

data class TaskItem(val id: Long, val task: String, val date: String, val isDone: Boolean) {
    companion object {
        fun fromDB(dbItem: TaskDbItem) = with(dbItem) {
            TaskItem(
                uid,
                task,
                date.orEmpty(),
                isDone
            )
        }
    }
}

