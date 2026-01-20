package com.rms.remember.shared.feature.tasks.presentation

import kotlinx.datetime.LocalDate

data class TaskDetailUiState(
    val date: String? = null,
    val taskId: Long? = null,
    val taskStr: String = ""
)

sealed interface TaskDetailIntent {

    data class SetDate(val dateStr: LocalDate) : TaskDetailIntent

    data class UpdateTask(val task: String) : TaskDetailIntent
}