package com.rms.remember.shared.feature.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramees.domain.TasksUseCase
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class TaskDetailsViewModelFactory(
    @Assisted val taskId: Long,
    private val tasksUseCase: TasksUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            return TaskDetailVM(tasksUseCase, taskId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }

    @AssistedFactory
    fun interface Factory {
        fun create(taskId: Long): TaskDetailsViewModelFactory
    }
}