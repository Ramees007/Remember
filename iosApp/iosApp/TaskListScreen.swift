import Combine
import KMPNativeCoroutinesAsync
import SharedIOSApi
import SwiftUI

struct TaskListScreen: View {

    let tasksViewModelFactory: TasksViewModelFactory
    let onAddTask: () -> Void
    let onOpenTask: (Int64) -> Void

    @State private var uiState: TasksUiState = TasksUiStateLoading()
    @StateObject private var viewModelStoreOwner = IosViewModelStoreOwner()
    @State private var task: Task<Void, Never>?

    var body: some View {
        
        let viewModel: TasksViewModel = viewModelStoreOwner.viewModel(
            factory: tasksViewModelFactory
        )

        Content(
            uiState: uiState,
            onAddTask: onAddTask,
            onDelete: { taskId in
                viewModel.handleIntent(
                    intent: TasksUiIntentDelete(taskId: taskId)
                )
            },
            onTaskClick: { taskId in
                onOpenTask(taskId)
            },
            onTaskCheckedChanged: { taskId, checked in
                viewModel.handleIntent(
                    intent: TasksUiIntentDone(taskId: taskId, selected: checked)
                )
            }
        )
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

private struct Content: View {

    let uiState: TasksUiState
    let onAddTask: () -> Void
    let onDelete: (Int64) -> Void
    let onTaskClick: (Int64) -> Void
    let onTaskCheckedChanged: (Int64, Bool) -> Void

    var body: some View {
        ZStack {
            VStack {
                switch uiState {
                case is TasksUiStateTasks:
                    let tasks = (uiState as! TasksUiStateTasks).tasks
                    List {
                        ForEach(tasks, id: \.id) { task in
                            TaskItemContent(
                                task: task,
                                onClick: onTaskClick,
                                onCheckedChanged: onTaskCheckedChanged
                            )
                        }.onDelete { indexSet in
                            indexSet.forEach { index in
                                onDelete(tasks[index].id)
                            }
                        }
                    }
                case is TasksUiStateLoading:
                    ProgressView()
                case is TasksUiStateEmpty:
                    // TODO make full screen and add graphics
                    Text("Empty")
                default:
                    // TODO un-necessary
                    Text("Default")
                }
            }
            .padding()
            .overlay(alignment: .bottomTrailing) {
                Button(action: onAddTask) {
                    Image(systemName: "plus")
                        .font(.title)
                        .foregroundColor(.white)
                        .padding()
                        .background(Circle().fill(.blue))
                        .shadow(radius: 4)
                }
                .padding()
            }
        }
    }
}

private struct TaskItemContent: View {

    let task: TaskItem
    let onClick: (Int64) -> Void
    let onCheckedChanged: (Int64, Bool) -> Void

    var body: some View {
        HStack(
            content: {
                Text(task.task)

                if !task.date.isEmpty {
                    Text(task.date)
                }

                Image(
                    systemName: task.isDone ? "checkmark.square.fill" : "square"
                ).onTapGesture {
                    onCheckedChanged(task.id, !task.isDone)
                }
            }
        ).onTapGesture {
            onClick(task.id)
        }
    }
}

#Preview {
    Content(
        uiState: TasksUiStateLoading(),
        onAddTask: {},
        onDelete: { _ in },
        onTaskClick: { _ in },
        onTaskCheckedChanged: { _, _ in }
    )
}
