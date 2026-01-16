package com.rms.remember

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rms.notes.ui.notesGraph
import com.rms.remember.bottom_nav.BottomNavItem
import com.rms.remember.bottom_nav.BottomNavigation
import com.rms.remember.di.AppGraph
import com.rms.remember.di.createAppGraph
import com.rms.remember.shared.util.platform
import com.rms.tasks.ui.tasksGraph
import com.rms.ui.theme.RememberTheme

class MainActivity : ComponentActivity() {

    private lateinit var appGraph: AppGraph

    override fun onCreate(savedInstanceState: Bundle?) {
        appGraph = createAppGraph(this)
        super.onCreate(savedInstanceState)
        setContent {

            LaunchedEffect(Unit) {
                Toast.makeText(this@MainActivity, "Platform: ${platform()}", Toast.LENGTH_LONG)
                    .show()
            }

            RememberTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FullScreenGraph(appGraph)
                }
            }
        }
    }
}

@Composable
fun FullScreenGraph(appGraph: AppGraph) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            MainScreenView(appGraph)
        }
    }
}

@Composable
fun MainScreenView(appGraph: AppGraph) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigation(navController = navController) }
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavigationGraph(
                navController = navController,
                appGraph = appGraph
            )
        }
    }
}


@Composable
fun NavigationGraph(
    navController: NavHostController,
    appGraph: AppGraph
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Tasks.route) {
        tasksGraph(navController, appGraph.tasksGraphFactory.createTasksGraph())
        notesGraph(navController, appGraph.notesGraphFactory.createNotesGraph())
    }
}


