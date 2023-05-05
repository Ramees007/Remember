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
                timeStamp?.formatAsDateString(DateFormat.DD_MM_YY).orEmpty(),
                isDone
            )
        }
    }
}

enum class DateFormat(val format: String) {
    DD_MM_YY("dd MM YY")
}

fun Long.formatAsDateString(dateFormat: DateFormat): String {
    val df = SimpleDateFormat(dateFormat.format, Locale.getDefault())
    return df.format(this)
}