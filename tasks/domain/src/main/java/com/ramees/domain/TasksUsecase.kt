package com.ramees.domain

import kotlinx.coroutines.flow.Flow

interface TasksUsecase {

    fun getAllTasks(): Flow<List<TaskItem>>

    suspend fun getTask(id: Long): TaskItem?

    suspend fun insert(txt: String, localDate: String?): Long

    suspend fun update(id: Long, isChecked: Boolean)

    suspend fun delete(taskId: Long)

    suspend fun update(id: Long, task: String, localDate: String?)
}