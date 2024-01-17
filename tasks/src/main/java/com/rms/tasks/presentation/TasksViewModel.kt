package com.rms.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramees.domain.TasksUseCase
import com.rms.tasks.model.toTaskItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val tasksUseCase: TasksUseCase) : ViewModel() {

    val flow: StateFlow<TasksUiState> = tasksUseCase.getAllTasks().map {
        if (it.isEmpty()) TasksUiState.Empty
        else TasksUiState.Tasks(it.map { it.toTaskItem() })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TasksUiState.Loading
    )

    fun handleIntent(intent: TasksUiIntent) {
        when (intent) {
            is TasksUiIntent.Delete -> {
                onDelete(intent.taskId)
            }

            is TasksUiIntent.Done -> {
                onCheckedChanged(intent.taskId, intent.selected)
            }
        }
    }

    private fun onCheckedChanged(id: Long, isChecked: Boolean) {
        viewModelScope.launch {
            tasksUseCase.update(id, isChecked)
        }
    }

    private fun onDelete(taskId: Long) {
        viewModelScope.launch {
            tasksUseCase.delete(taskId)
        }
    }
}