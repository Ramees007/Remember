package presentation

import dev.zacsweers.metro.Inject
import domain.TasksUseCase

@Inject
class TaskDetailVMAssistedFactory constructor(private val tasksUseCase: TasksUseCase) {

    fun create(taskId: Long): TaskDetailVM = TaskDetailVM(tasksUseCase, taskId)
}