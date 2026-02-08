import Combine
import KMPNativeCoroutinesAsync
import SharedIOSApi
import SwiftUI

struct TaskDetailsScreen: View {

    let taskDetailVmFactory: TaskDetailVMFactory

    var id: Int64?
    @StateObject private var viewModelStoreOwner = IosViewModelStoreOwner()
    @State private var uiState: TaskDetailUiState = TaskDetailUiState(
        date: nil,
        taskId: nil,
        taskStr: ""
    )
    @State private var taskText: String = ""
    @State private var selectedDateTime = Date()

    var body: some View {

        let viewModel = viewModel()

        VStack {
            FullScreenTextInput(
                text: $taskText,
                onTextChange: { txt in
                    viewModel.handleIntent(
                        intent: TaskDetailIntentUpdateTask(task: txt)
                    )
                }
            )
        }.task {
            await observeState(viewModel: viewModel)
        }.toolbar {
            ToolbarItem(placement: .topBarTrailing) {
                DatePicker(
                    "Select Date and Time",
                    selection: $selectedDateTime,
                    displayedComponents: [.date, .hourAndMinute]
                )
            }
        }

    }

    private func viewModel() -> TaskDetailVM {
        let taskId = id ?? 0
        return viewModelStoreOwner.viewModel(
            factory: AssistedViewModelFactoriesKt.taskDetailsViewModelFactory(
                vmCreator: { taskDetailVmFactory.create(taskId: taskId) })
        )
    }

    private func observeState(viewModel: TaskDetailVM) async {
        do {
            let sequence = asyncSequence(for: viewModel.taskState)
            for try await state in sequence {
                self.uiState = state
                taskText = state.taskStr
            }
        } catch is CancellationError {
        } catch {
            print(error)
        }
    }
}

struct FullScreenTextInput: View {

    @Binding var text: String
    @FocusState private var isFocused: Bool
    let onTextChange: (String) -> Void

    var body: some View {
        TextEditor(text: $text)
            .font(.body)
            .padding()
            .focused($isFocused)
            .onChange(
                of: text,
                { oldValue, newValue in
                    onTextChange(newValue)
                }
            )
            .onAppear {
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.3) {
                    isFocused = true
                }
            }
    }
}

struct DateTimePickerView: View {
    @State private var selectedDateTime = Date()

    var body: some View {
        Form {
            DatePicker(
                "Select Date and Time",
                selection: $selectedDateTime,
                displayedComponents: [.date, .hourAndMinute]
            )
            
            Text("Selected: \(selectedDateTime.formatted())")
        }
    }
}
