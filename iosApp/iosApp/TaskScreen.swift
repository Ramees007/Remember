import SwiftUI
import SharedIOSApi
import Combine
import KMPNativeCoroutinesAsync

struct TaskScreen: View {
    
    let taskGraph: TasksGraph
    
    @State private var navPath = NavigationPath()
    
    var body: some View {
        
        NavigationStack(path: $navPath) {
            TaskListScreen(tasksViewModelFactory: taskGraph.tasksVmFactory) {
                navPath.append(TaskRoute.details(id: nil))
            }.navigationDestination(for: TaskRoute.self) { route in
                switch route{
                case TaskRoute.list:
                    TaskListScreen(tasksViewModelFactory: taskGraph.tasksVmFactory) {
                        navPath.append(TaskRoute.details(id: nil))
                    }
                case TaskRoute.details(let id):
                    TaskDetailsScreen(taskDetailVmFactory: taskGraph.taskDetailsVmFactory, id: id)
                }
            }
        }
    }
}

enum TaskRoute: Hashable {
    case list
    case details(id: Int64?)
}




#Preview {
    TaskScreen(taskGraph: IosAppGraphHolder.shared.appGraph.tasksGraphFactory.createTasksGraph())
}
