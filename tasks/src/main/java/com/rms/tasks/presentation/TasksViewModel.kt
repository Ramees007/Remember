package com.rms.tasks.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rms.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    val flow: StateFlow<TasksUiState> = taskRepository.getAllTasks().map {
        if (it.isEmpty()) TasksUiState.Empty
        else TasksUiState.Tasks(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = TasksUiState.Loading
    )

    fun onCheckedChanged(id: Long, isChecked: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.update(id, isChecked)
            }
        }
    }

    fun save(txt: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                taskRepository.insert(txt)
            }
        }
    }

}