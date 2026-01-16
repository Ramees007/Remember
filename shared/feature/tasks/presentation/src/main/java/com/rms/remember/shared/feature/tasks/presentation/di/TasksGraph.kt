package com.rms.remember.shared.feature.tasks.presentation.di

import com.ramees.domain.TasksUseCase
import com.ramees.domain.TasksUseCaseImpl
import com.rms.data.TaskRepository
import com.rms.data.TaskRepositoryImpl
import com.rms.db.AppDataBase
import com.rms.db.dao.TaskDao
import com.rms.remember.shared.feature.tasks.presentation.TaskDetailsViewModelFactory
import com.rms.remember.shared.feature.tasks.presentation.TasksViewModelFactory
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.Scope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@GraphExtension(TasksScope::class)
interface TasksGraph {

    val tasksViewModelFactory: TasksViewModelFactory

    val taskDetailsViewModelFactoryFactory: TaskDetailsViewModelFactory.Factory

    @Binds val TasksUseCaseImpl.Bind: TasksUseCase

    @Binds val TaskRepositoryImpl.Bind: TaskRepository

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