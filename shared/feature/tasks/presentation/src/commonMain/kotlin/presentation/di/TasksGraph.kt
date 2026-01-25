package presentation.di


import data.TaskRepository
import data.TaskRepositoryImpl
import db.AppDataBase
import db.dao.TaskDao
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.Scope
import domain.TasksUseCase
import domain.TasksUseCaseImpl
import kotlinx.coroutines.CoroutineDispatcher

@GraphExtension(TasksScope::class)
interface TasksGraph {

    //val tasksViewModelFactory: TasksViewModelFactory

//    @Provides
//    fun taskDetailsViewModelFactory(
//        taskId: Long,
//        tasksUseCase: TasksUseCase
//    ) = viewModelFactory {
//        initializer {
//            TaskDetailVM(tasksUseCase, taskId)
//        }
//    }

    val tasksUseCase: TasksUseCase

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