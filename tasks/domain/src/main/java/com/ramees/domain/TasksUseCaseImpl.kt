package com.ramees.domain

import com.rms.data.TaskRepository
import com.rms.data.model.TaskEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import toLocalDate

class TasksUseCaseImpl constructor(
    private val taskRepository: TaskRepository,
    private val ioDispatcher: CoroutineDispatcher
) :
    TasksUseCase {

    override fun getAllTasks(): Flow<List<TaskEntity>> {
        return taskRepository.getAllTasks().map { tasks ->
            tasks.filter { !it.isDone }
                .sortedWith(compareBy(nullsLast()) { task ->
                    task.date.takeIf { !it.isNullOrEmpty() }?.toLocalDate()
                })
        }.flowOn(ioDispatcher)
    }

    override suspend fun getTask(id: Long): TaskEntity? {
        return withContext(ioDispatcher) {
            taskRepository.getTask(id)
        }
    }

    override suspend fun insert(txt: String, localDate: String?): Long {
        return withContext(ioDispatcher) {
            taskRepository.insert(txt, localDate)
        }
    }

    override suspend fun update(id: Long, isChecked: Boolean) {
        withContext(ioDispatcher) {
            taskRepository.update(id, isChecked)
        }
    }

    override suspend fun update(id: Long, task: String, localDate: String?) {
        withContext(ioDispatcher) {
            taskRepository.update(id, task, localDate)
        }
    }

    override suspend fun delete(taskId: Long) {
        withContext(ioDispatcher) {
            taskRepository.delete(taskId)
        }
    }


}