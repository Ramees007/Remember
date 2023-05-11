package com.rms.data

import kotlinx.coroutines.flow.Flow


interface TaskRepository {
    fun getAllTasks(): Flow<List<TaskItem>>
    suspend fun getTask(taskId: Long): TaskItem?
    suspend fun insert(txt: String, localDate: String?)
    suspend fun update(id: Long, isChecked: Boolean)
    suspend fun update(id: Long, task: String, localDate: String?)
}