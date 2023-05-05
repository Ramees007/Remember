package com.rms.data

import kotlinx.coroutines.flow.Flow
import java.time.LocalDate


interface TaskRepository {
    fun getAllTasks(): Flow<List<TaskItem>>
    suspend fun insert(txt: String, localDate: LocalDate?)
    suspend fun update(id: Long, isChecked: Boolean)
}