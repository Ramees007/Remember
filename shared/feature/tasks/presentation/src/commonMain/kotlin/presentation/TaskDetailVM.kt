package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.TasksUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDate
import toDateString

class TaskDetailVM(
    private val tasksUseCase: TasksUseCase,
    private val taskId: Long
) : ViewModel() {

    private val _taskState: MutableStateFlow<TaskDetailUiState> = MutableStateFlow(
        TaskDetailUiState(
            taskId = taskId
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
            tasksUseCase.getTask(taskId)?.let { task ->
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
            taskId?.takeIf { it != 0L }?.let {
                tasksUseCase.update(it, taskTxt, taskDate)
            } ?: run {
                val taskId = tasksUseCase.insert(taskTxt, taskDate)
                _taskState.emit(_taskState.value.copy(taskId = taskId))
            }
        }
    }
}