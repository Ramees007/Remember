package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import dev.zacsweers.metro.Inject
import domain.TasksUseCase
import kotlin.reflect.KClass

@Inject
class TasksViewModelFactory(private val tasksUseCase: TasksUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
        return TasksViewModel(tasksUseCase) as T
    }
}