package com.rms.tasks.di

import com.ramees.domain.TasksUsecase
import com.ramees.domain.TasksUsecaseImpl
import com.rms.data.TaskRepository
import com.rms.data.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TasksModule {

    @Binds
    abstract fun bindRepo(
        repo: TaskRepositoryImpl
    ): TaskRepository

    @Binds
    abstract fun bindUsecase(usecase: TasksUsecaseImpl): TasksUsecase
}