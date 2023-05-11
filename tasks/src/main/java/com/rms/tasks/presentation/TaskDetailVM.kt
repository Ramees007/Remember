package com.rms.tasks.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rms.android_util.toDateString
import com.rms.data.TaskItem
import com.rms.data.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TaskDetailVM @Inject constructor(
    private val taskRepository: TaskRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val taskId: Long? = savedStateHandle.get<String?>("taskId")?.toLongOrNull()
    var taskDetailText by mutableStateOf("")
        private set

    private val _taskDate: MutableStateFlow<String?> = MutableStateFlow(null)
    val taskDate: StateFlow<String?>
        get() = _taskDate

    private val _saveState: MutableStateFlow<Unit?> = MutableStateFlow(null)
    val saveState: StateFlow<Unit?>
        get() = _saveState

    init {
        viewModelScope.launch {
            val task: TaskItem? = withContext(Dispatchers.IO) {
                taskId?.let { taskId ->
                    taskRepository.getTask(taskId)
                }
            }
            taskDetailText = task?.task.orEmpty()
            _taskDate.value = task?.date
        }
    }

    fun save() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                taskId?.let {
                    taskRepository.update(it, taskDetailText, taskDate.value)
                } ?: taskRepository.insert(taskDetailText, taskDate.value)
            }
            _saveState.value = Unit
        }
    }

    fun setTime(localDate: LocalDate) {
        _taskDate.value = localDate.toDateString()
    }

    fun updateText(txt: String) {
        taskDetailText = txt
    }
}