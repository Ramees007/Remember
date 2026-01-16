package com.rms.remember.shared.feature.tasks.presentation

import com.rms.data.model.TaskEntity

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

fun TaskEntity.toTaskItem() = TaskItem(id, task, date.orEmpty(), isDone, extractStatus())

private fun TaskEntity.extractStatus(): TaskStatus = when {
    isDone -> TaskStatus.Done
    date.isNullOrEmpty() -> TaskStatus.Future
    // TODO fixme
    //date!!.toLocalDate().isEqual(LocalDate.now()) -> TaskStatus.TodaysUnDone
    //date!!.toLocalDate().isBefore(LocalDate.now()) -> TaskStatus.PastUnDone
    else -> TaskStatus.Future
}
