package com.rms.data

import com.rms.android_util.toDateString
import com.rms.db.dao.TaskDao
import com.rms.db.model.TaskDbItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val dao: TaskDao) :
    TaskRepository {

    override fun getAllTasks(): Flow<List<TaskItem>> {
        return dao.getAll().map {
            it.map { dbItem -> TaskItem.fromDB(dbItem) }
        }
    }

    override suspend fun insert(txt: String, localDate: LocalDate?) {
        dao.insertAll(
            TaskDbItem(
                task = txt,
                date = localDate?.toDateString(),
                isDone = false
            )
        )
    }

    override suspend fun update(id: Long, isChecked: Boolean) {
        dao.update(id, isChecked)
    }


}