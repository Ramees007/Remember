package com.rms.db.di

import android.content.Context
import androidx.room.Room
import com.rms.db.AppDataBase
import com.rms.db.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun provideDb(@ApplicationContext context: Context): AppDataBase = Room.databaseBuilder(
        context,
        AppDataBase::class.java,
        AppDataBase.NAME
    ).build()

    @Provides
    @Singleton
    fun provideTaskDao(db: AppDataBase): TaskDao = db.taskDao()
}