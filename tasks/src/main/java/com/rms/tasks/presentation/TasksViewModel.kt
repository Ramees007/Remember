package com.rms.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramees.domain.TasksUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val tasksUsecase: TasksUsecase) : ViewModel() {

    val flow: StateFlow<TasksUiState> = tasksUsecase.getAllTasks().map {
        if (it.isEmpty()) TasksUiState.Empty
        else TasksUiState.Tasks(it)
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
            tasksUsecase.update(id, isChecked)
        }
    }

    private fun onDelete(taskId: Long) {
        viewModelScope.launch {
            tasksUsecase.delete(taskId)
        }
    }
}