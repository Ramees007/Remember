package com.rms.remember.di

import android.content.Context
import com.rms.db.AppDataBase
import com.rms.db.dao.NotesDao
import com.rms.db.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import getDatabaseBuilder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDataBase = getDatabaseBuilder(context).build()

    @Provides
    @Singleton
    fun provideTaskDao(db: AppDataBase): TaskDao = db.taskDao()

    @Provides
    @Singleton
    fun providesNotesDao(db: AppDataBase): NotesDao = db.notestDao()
}