package com.rms.remember

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
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
            MainScreenView()
        }
    }
}

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavigationGraph(
                navController = navController
            )
        }
    }
}


@Composable
fun NavigationGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Tasks.route) {
        tasksGraph(navController)
        notesGraph(navController)
    }
}

fun NavGraphBuilder.tasksGraph(navController: NavController) {
    navigation(startDestination = "tasksList", route = BottomNavItem.Tasks.route) {
        composable("tasksList") {
            TasksRoute(
                navController = navController
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
    }
}

fun NavGraphBuilder.notesGraph(navController: NavController) {
    navigation(startDestination = "notesList", route = BottomNavItem.Notes.route) {
        composable("notesList") {
            NotesRoute(
                navController = navController
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