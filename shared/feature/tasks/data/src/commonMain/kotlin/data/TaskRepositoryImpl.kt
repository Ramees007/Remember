package data

import data.model.TaskEntity
import db.dao.TaskDao
import db.model.TaskDbItem
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Inject
class TaskRepositoryImpl(private val dao: TaskDao) :
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

fun TaskDbItem.toEntity() = TaskEntity(uid, task, date.orEmpty(), isDone, doneDate)