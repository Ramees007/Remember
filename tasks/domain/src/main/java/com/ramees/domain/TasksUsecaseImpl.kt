package com.ramees.domain

import com.rms.data.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import toLocalDate
import javax.inject.Inject

class TasksUsecaseImpl @Inject constructor(private val taskRepository: TaskRepository) :
    TasksUsecase {

    override fun getAllTasks(): Flow<List<TaskItem>> {
        return taskRepository.getAllTasks().map { tasks ->
            tasks.filter { !it.isDone }.map { it.toDomainModel() }
                .sortedWith(compareBy(nullsLast()) { task ->
                    task.date.takeIf { it.isNotEmpty() }?.toLocalDate()
                })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getTask(id: Long): TaskItem? {
        return withContext(Dispatchers.IO) {
            taskRepository.getTask(id)?.toDomainModel()
        }
    }

    override suspend fun insert(txt: String, localDate: String?) {
        withContext(Dispatchers.IO) {
            taskRepository.insert(txt, localDate)
        }
    }

    override suspend fun update(id: Long, isChecked: Boolean) {
        withContext(Dispatchers.IO) {
            taskRepository.update(id, isChecked)
        }
    }

    override suspend fun update(id: Long, task: String, localDate: String?) {
        withContext(Dispatchers.IO) {
            taskRepository.update(id, task, localDate)
        }
    }

    override suspend fun delete(taskId: Long) {
        withContext(Dispatchers.IO) {
            taskRepository.delete(taskId)
        }
    }


}