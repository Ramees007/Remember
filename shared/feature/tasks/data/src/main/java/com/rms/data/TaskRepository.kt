package com.rms.data

import com.rms.data.model.TaskEntity
import kotlinx.coroutines.flow.Flow


interface TaskRepository {
    fun getAllTasks(): Flow<List<TaskEntity>>
    suspend fun getTask(taskId: Long): TaskEntity?
    suspend fun insert(txt: String, localDate: String?): Long
    suspend fun update(id: Long, isChecked: Boolean)
    suspend fun delete(taskId: Long)
    suspend fun update(id: Long, task: String, localDate: String?)
}