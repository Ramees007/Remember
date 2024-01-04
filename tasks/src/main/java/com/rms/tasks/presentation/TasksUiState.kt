package com.rms.tasks.presentation

import com.rms.tasks.model.TaskItem

sealed interface TasksUiState {
    object Loading : TasksUiState
    data class Tasks(val tasks: List<TaskItem>) : TasksUiState
    object Empty : TasksUiState
}

sealed interface TasksUiIntent{

    data class Done(val taskId: Long, val selected: Boolean): TasksUiIntent

    data class Delete(val taskId: Long): TasksUiIntent
}