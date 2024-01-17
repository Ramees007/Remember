package com.rms.tasks.repository

import com.rms.data.TaskRepository
import com.rms.data.model.TaskEntity
import com.rms.db.dao.TaskDao
import com.rms.db.model.TaskDbItem
import com.rms.tasks.model.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dao: TaskDao) :
    TaskRepository {

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return dao.getAll().map {
            it.map { task ->
                task.toEntity()
            }
        }
    }

    override suspend fun insert(txt: String, localDate: String?): Long {
        return dao.insert(
            TaskDbItem(
                task = txt,
                date = localDate,
                isDone = false
            )
        )
    }

    override suspend fun update(id: Long, isChecked: Boolean) {
        dao.update(id, isChecked)
    }

    override suspend fun getTask(taskId: Long): TaskEntity? {
        return dao.getTask(taskId)?.toEntity()
    }

    override suspend fun update(id: Long, task: String, localDate: String?) {
        dao.update(id, task, localDate)
    }

    override suspend fun delete(taskId: Long) {
        dao.deleteById(taskId)
    }
}