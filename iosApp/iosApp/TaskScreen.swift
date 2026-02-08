import Combine
import KMPNativeCoroutinesAsync
import SharedIOSApi
import SwiftUI

struct TaskScreen: View {

    let taskGraph: TasksGraph

    @State private var navPath = NavigationPath()

    var body: some View {

        NavigationStack(path: $navPath) {
            TaskList()
                .navigationDestination(for: TaskRoute.self) { route in
                    switch route {
                    case TaskRoute.list:
                        TaskList()
                    case TaskRoute.details(let id):
                        TaskDetailsScreen(
                            taskDetailVmFactory: taskGraph.taskDetailsVmFactory,
                            id: id
                        )
                    }
                }
        }
    }

    private func TaskList() -> TaskListScreen {
        return TaskListScreen(
            tasksViewModelFactory: taskGraph.tasksVmFactory,
            onAddTask: {
                navPath.append(TaskRoute.details(id: nil))
            },
            onOpenTask: { taskId in
                navPath.append(TaskRoute.details(id: taskId))
            }
        )
    }
}

enum TaskRoute: Hashable {
    case list
    case details(id: Int64?)
}

#Preview {
    TaskScreen(
        taskGraph: IosAppGraphHolder.shared.appGraph.tasksGraphFactory
            .createTasksGraph()
    )
}
