package com.rms.data

import com.rms.db.dao.TaskDao
import com.rms.db.model.TaskDbItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dao: TaskDao) :
    TaskRepository {

    override fun getAllTasks(): Flow<List<TaskItem>> {
        return dao.getAll().map {
            it.map { dbItem -> dbItem.toPresentation() }
        }
    }

    override suspend fun insert(txt: String, localDate: String?) {
        dao.insertAll(
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

    override suspend fun getTask(taskId: Long): TaskItem? {
        return dao.getTask(taskId)?.toPresentation()
    }

    override suspend fun update(id: Long, task: String, localDate: String?) {
        dao.update(id, task, localDate)
    }
}