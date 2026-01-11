package com.rms.tasks.di

import com.ramees.domain.TasksUseCase
import com.ramees.domain.TasksUseCaseImpl
import com.rms.data.TaskRepository
import com.rms.data.TaskRepositoryImpl
import com.rms.db.dao.TaskDao
import com.rms.remember.core.domain.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object TasksModule {

    @Provides
    fun providesRepo(dao: TaskDao): TaskRepository = TaskRepositoryImpl(dao)

    @Provides
    fun providesUseCase(
        repo: TaskRepository,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): TasksUseCase = TasksUseCaseImpl(repo, dispatcher)
}