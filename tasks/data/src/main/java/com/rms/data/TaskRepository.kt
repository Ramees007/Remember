package com.rms.data

import com.rms.db.model.TaskDbItem
import kotlinx.coroutines.flow.Flow


interface TaskRepository {
    fun getAllTasks(): Flow<List<TaskDbItem>>
    suspend fun getTask(taskId: Long): TaskDbItem?
    suspend fun insert(txt: String, localDate: String?): Long
    suspend fun update(id: Long, isChecked: Boolean)
    suspend fun delete(taskId: Long)
    suspend fun update(id: Long, task: String, localDate: String?)
}