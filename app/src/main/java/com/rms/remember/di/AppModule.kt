package com.rms.remember.di

import com.rms.data.TaskRepository
import com.rms.data.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindRepo(
        repo: TaskRepositoryImpl
    ): TaskRepository


}