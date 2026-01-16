package com.rms.remember.shared.feature.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ramees.domain.TasksUseCase
import dev.zacsweers.metro.Inject

@Inject
class TasksViewModelFactory(private val tasksUseCase: TasksUseCase) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TasksViewModel::class.java)) {
            return TasksViewModel(tasksUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}