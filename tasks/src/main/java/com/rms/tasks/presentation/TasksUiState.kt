package com.rms.tasks.presentation

import com.ramees.domain.TaskItem

sealed interface TasksUiState {
    object Loading : TasksUiState
    data class Tasks(val tasks: List<TaskItem>) : TasksUiState
    object Empty : TasksUiState
}