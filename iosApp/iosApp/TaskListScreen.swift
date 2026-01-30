import SwiftUI
import SharedIOSApi
import Combine
import KMPNativeCoroutinesAsync


struct TaskListScreen : View {
    
    let tasksViewModelFactory: TasksViewModelFactory
    let onAddTask: () -> Void
    
    @State private var uiState: TasksUiState = TasksUiStateLoading()
    @StateObject private var viewModelStoreOwner = IosViewModelStoreOwner()
    @State private var task: Task<Void, Never>?
    
    var body: some View {
        let viewModel: TasksViewModel = viewModelStoreOwner.viewModel(factory: tasksViewModelFactory)
        
        VStack {
            switch uiState{
            case is TasksUiStateTasks:
                List{
                    ForEach((uiState as! TasksUiStateTasks).tasks, id: \.id){task in
                        Text("Task ID: \(task.id) Text: \(task.task)")
                    }
                }
            case is TasksUiStateLoading:
                ProgressView()
            case is TasksUiStateEmpty:
                Text("Empty")
            default:
                // TODO check
                Text("Default")
            }
            
            Button("Add") {
                onAddTask()
            }
        }
        .padding()
        .task {
            await observeState(viewModel: viewModel)
        }
    }
    
    private func observeState(viewModel: TasksViewModel) async {
            do {
                let sequence = asyncSequence(for: viewModel.flow)
                for try await state in sequence {
                    self.uiState = state
                }
            } catch is CancellationError {
            } catch {
                print(error)
            }
        }
}
