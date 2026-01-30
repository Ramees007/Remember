package presentation.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import data.TaskRepository
import data.TaskRepositoryImpl
import db.AppDataBase
import db.dao.TaskDao
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.Scope
import domain.TasksUseCase
import domain.TasksUseCaseImpl
import presentation.TaskDetailVM
import presentation.TasksViewModelFactory

@GraphExtension(TasksScope::class)
interface TasksGraph {
    
    val taskDetailsVmFactory: TaskDetailVM.Factory

    val tasksVmFactory: TasksViewModelFactory

    @Binds
    val TasksUseCaseImpl.Bind: TasksUseCase

    @Binds
    val TaskRepositoryImpl.Bind: TaskRepository

    @Provides
    fun taskDao(db: AppDataBase): TaskDao = db.taskDao()


    @ContributesTo(AppScope::class)
    @GraphExtension.Factory
    interface Factory {
        fun createTasksGraph(): TasksGraph
    }
}

@Scope
annotation class TasksScope

inline fun <reified T : ViewModel> createViewModelFactory(
    crossinline vmCreator: () -> T
): ViewModelProvider.Factory = viewModelFactory {
    initializer<T> {
        vmCreator()
    }
}