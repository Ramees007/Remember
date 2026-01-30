import SwiftUI
import SharedIOSApi

struct TaskDetailsScreen: View {
    
    let taskDetailVmFactory: TaskDetailVMFactory
    
    var id: Int64?
    @StateObject private var viewModelStoreOwner = IosViewModelStoreOwner()
    
    var body: some View {
        
        let viewModel = viewModel()
        
        VStack {
            Text("Hello Tasks Details")
            
            Button("Save"){
                viewModel.handleIntent(intent: TaskDetailIntentUpdateTask(task: "Save Task with ID:\(id, default: "0") & Time:\(Calendar.current)"))
            }
        }
        
    }
    
    private func viewModel() -> TaskDetailVM {
        let taskId = id ?? 0
        return viewModelStoreOwner.viewModel(
            factory: AssistedViewModelFactoriesKt.taskDetailsViewModelFactory(vmCreator: {taskDetailVmFactory.create(taskId: taskId )})
        )
    }
}

