package com.ramees.domain

import com.rms.db.model.TaskDbItem
import toLocalDate
import java.time.LocalDate
import javax.annotation.concurrent.Immutable

@Immutable
data class TaskItem(
    val id: Long,
    val task: String,
    val date: String,
    val isDone: Boolean,
    val status: TaskStatus
)

enum class TaskStatus {
    PastUnDone, TodaysUnDone, Done, Future
}

fun TaskDbItem.toDomainModel() = TaskItem(uid, task, date.orEmpty(), isDone, extractStatus())

private fun TaskDbItem.extractStatus(): TaskStatus = when {
    isDone -> TaskStatus.Done
    date.isNullOrEmpty() -> TaskStatus.Future
    date!!.toLocalDate().isEqual(LocalDate.now()) -> TaskStatus.TodaysUnDone
    date!!.toLocalDate().isBefore(LocalDate.now()) -> TaskStatus.PastUnDone
    else -> TaskStatus.Future
}
