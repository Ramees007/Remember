import SwiftUI
import SharedIOSApi

struct RootView: View {

    @State private var selectedTab: BottomTab = .tasks
    
    private var taskGraph = IosAppGraphHolder.shared.appGraph.tasksGraphFactory.createTasksGraph()

    var body: some View {
        TabView(selection: $selectedTab) {

            TaskScreen(taskGraph: taskGraph)
                .tabItem {
                    Label("Tasks", systemImage: "checklist")
                }
                .tag(BottomTab.tasks)

            NotesScreen()
                .tabItem {
                    Label("Notes", systemImage: "note.text")
                }
                .tag(BottomTab.notes)

        }
    }
}


