package com.rms.tasks.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramees.domain.TaskItem
import com.ramees.domain.TaskStatus
import com.ramees.domain.TasksUsecase
import com.rms.tasks.ui.TASK_ID_PARAM_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import toDateString
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TaskDetailVM @Inject constructor(
    private val tasksUsecase: TasksUsecase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _taskState: MutableStateFlow<TaskDetailUiState> = MutableStateFlow(
        TaskDetailUiState(
            taskId = savedStateHandle.get<String?>(TASK_ID_PARAM_KEY)?.toLongOrNull()
        )
    )
    val taskState: StateFlow<TaskDetailUiState>
        get() = _taskState

    init {
        fetchTask()
    }

    fun handleIntent(intent: TaskDetailIntent) {
        when (intent) {
            is TaskDetailIntent.UpdateTask -> {
                updateText(intent.task)
            }

            is TaskDetailIntent.SetDate -> {
                setDate(intent.dateStr)
            }
        }
    }

    private fun fetchTask() {
        viewModelScope.launch {
            val taskId = _taskState.value.taskId ?: return@launch
            tasksUsecase.getTask(taskId)?.let { task ->
                _taskState.emit(
                    _taskState.value.copy(
                        taskId = task.id,
                        date = task.date,
                        taskStr = task.task
                    )
                )
            }
        }
    }

    private fun setDate(localDate: LocalDate) {
        val taskDate = localDate.toDateString()
        _taskState.tryEmit(_taskState.value.copy(date = taskDate))
        save(_taskState.value.taskStr, taskDate)
    }

    private fun updateText(txt: String) {
        _taskState.tryEmit(_taskState.value.copy(taskStr = txt))
        save(txt, _taskState.value.date)
    }

    private fun save(taskTxt: String, taskDate: String?) {
        viewModelScope.launch {
            val task = _taskState.value
            val taskId = task.taskId
            taskId?.let {
                tasksUsecase.update(it, taskTxt, taskDate)
            } ?: run {
                val taskId = tasksUsecase.insert(taskTxt, taskDate)
                _taskState.emit(_taskState.value.copy(taskId = taskId))
            }
        }
    }
}