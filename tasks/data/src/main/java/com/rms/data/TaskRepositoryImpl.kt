package com.rms.data

import com.rms.db.dao.TaskDao
import com.rms.db.model.TaskDbItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dao: TaskDao) :
    TaskRepository {

    override fun getAllTasks(): Flow<List<TaskDbItem>> {
        return dao.getAll()
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

    override suspend fun getTask(taskId: Long): TaskDbItem? {
        return dao.getTask(taskId)
    }

    override suspend fun update(id: Long, task: String, localDate: String?) {
        dao.update(id, task, localDate)
    }

    override suspend fun delete(taskId: Long) {
        dao.deleteById(taskId)
    }
}