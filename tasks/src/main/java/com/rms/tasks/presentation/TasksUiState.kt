package com.rms.tasks.presentation

import com.rms.data.TaskItem

sealed interface TasksUiState {
    object Loading : TasksUiState
    data class Tasks(val tasks: List<TaskItem>) : TasksUiState
    object Empty : TasksUiState
}