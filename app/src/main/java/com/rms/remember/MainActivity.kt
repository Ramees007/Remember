package com.rms.remember

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.rms.notes.ui.NoteDetailsScreen
import com.rms.notes.ui.NotesRoute
import com.rms.remember.bottom_nav.BottomNavItem
import com.rms.remember.bottom_nav.BottomNavigation
import com.rms.tasks.ui.TaskDetailsScreen
import com.rms.tasks.ui.TasksRoute
import com.rms.ui.theme.RememberTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RememberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FullScreenGraph()
                }
            }
        }
    }
}

@Composable
fun FullScreenGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainScreenView(
                onNavigateToAddTask = {
                    navController.navigate("taskDetail")
                },
                onNavigateToTaskDetail = {
                    navController.navigate("taskDetail?taskId=$it")
                },
                onNavigateToAddNote = {
                    navController.navigate("noteDetail")
                },
                onNavigateToNoteDetails = {
                    navController.navigate("noteDetail?noteId=$it")
                }
            )
        }
        composable(
            "taskDetail?taskId={taskId}",
            arguments = listOf(navArgument("taskId") { nullable = true })
        ) {
            TaskDetailsScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }

        composable(
            "noteDetail?noteId={noteId}",
            arguments = listOf(navArgument("noteId") { nullable = true })
        ) {
            NoteDetailsScreen(onBack = {
                navController.navigateUp()
            })
        }
    }
}

@Composable
fun MainScreenView(
    onNavigateToAddTask: () -> Unit,
    onNavigateToAddNote: () -> Unit,
    onNavigateToTaskDetail: (taskId: Long) -> Unit,
    onNavigateToNoteDetails: (id: Long) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) },
        floatingActionButton = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            FloatingActionButton(onClick = {
                when (navBackStackEntry?.destination?.route) {

                    BottomNavItem.Tasks.route -> {
                        onNavigateToAddTask()
                    }

                    BottomNavItem.Notes.route -> {
                        onNavigateToAddNote()
                    }
                }
            }) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) {
        NavigationGraph(
            navController = navController,
            padding = it,
            onNavigateToTaskDetail = onNavigateToTaskDetail,
            onNavigateToNoteDetails = onNavigateToNoteDetails
        )
    }
}


@Composable
fun NavigationGraph(
    navController: NavHostController,
    padding: PaddingValues,
    onNavigateToTaskDetail: (taskId: Long) -> Unit,
    onNavigateToNoteDetails: (id: Long) -> Unit
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Tasks.route) {

        //tasksGraph(navController, padding, app)
        composable(BottomNavItem.Tasks.route) {
            TasksRoute(
                modifier = Modifier.padding(padding),
                onNavigateToTaskDetail = onNavigateToTaskDetail
            )
        }
        composable(BottomNavItem.Notes.route) {
            NotesRoute(
                modifier = Modifier.padding(padding),
                onNavigateToNoteDetails = onNavigateToNoteDetails
            )
        }
    }
}

//fun NavGraphBuilder.tasksGraph(navController: NavController, padding: PaddingValues, app: Context) {
//    navigation(startDestination = BottomNavItem.Tasks.route, route = "tasksGraph") {
//        composable(BottomNavItem.Tasks.route) {
//            val vm = daggerViewModel { app.taskVm() }
//            TasksScreen(Modifier.padding(padding), vm)
//        }
//        composable("taskDetail") {
//            TaskDetailsScreen()
//        }
//
//    }
//}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    RememberTheme {
//        MainScreenView()
//    }
//}