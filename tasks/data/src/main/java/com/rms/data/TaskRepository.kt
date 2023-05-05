package com.rms.data

import kotlinx.coroutines.flow.Flow


interface TaskRepository {
    fun getAllTasks(): Flow<List<TaskItem>>
    suspend fun insert(txt: String, timeStamp: Long?)
    suspend fun update(id: Long, isChecked: Boolean)
}