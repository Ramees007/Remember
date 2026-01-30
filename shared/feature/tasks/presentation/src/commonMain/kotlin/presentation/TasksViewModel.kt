package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import domain.TasksUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TasksViewModel(private val tasksUseCase: TasksUseCase) : ViewModel() {

    @NativeCoroutines
    val flow: StateFlow<TasksUiState> = tasksUseCase.getAllTasks().map {
        if (it.isEmpty()) TasksUiState.Empty
        else TasksUiState.Tasks(it.map { it.toTaskItem() })
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
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

    // TODO replace with ios flow support
    fun observeState(onChange: (TasksUiState) -> Unit) =
        flow.onEach(onChange).launchIn(viewModelScope)

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