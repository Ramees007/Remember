package com.rms.data

import kotlinx.coroutines.flow.Flow


interface TaskRepository {
    fun getAllTasks(): Flow<List<TaskItem>>
    suspend fun insert(txt: String)
    suspend fun update(id: Long, isChecked: Boolean)
}