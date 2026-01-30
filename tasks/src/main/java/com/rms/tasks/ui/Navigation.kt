package com.rms.tasks.ui

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import presentation.TaskDetailVM
import presentation.TasksViewModel
import presentation.di.TasksGraph
import presentation.taskDetailsViewModelFactory

const val TASKS_GRAPH_ROUTE = "tasks"
internal const val TASK_ID_PARAM_KEY = "taskId"

private const val TASKS_LIST_ROUTE = "tasksList"
private const val TASKS_DETAIL_ROUTE_PATTERN = "taskDetail?taskId={taskId}"
private const val TASK_DETAIL_ROUTE = "taskDetail?taskId="
fun NavGraphBuilder.tasksGraph(navController: NavController, taskGraph: TasksGraph) {
    navigation(startDestination = TASKS_LIST_ROUTE, route = TASKS_GRAPH_ROUTE) {
        taskListScreen(navController, taskGraph)
        taskDetailScreen(navController, taskGraph)
    }
}

private fun NavGraphBuilder.taskListScreen(navController: NavController, taskGraph: TasksGraph) {
    composable(TASKS_LIST_ROUTE) {
        val viewModel: TasksViewModel = viewModel(factory = taskGraph.tasksVmFactory)
        val uiState = viewModel.flow.collectAsStateWithLifecycle()
        TasksRoute(
            uiState = uiState.value,
            onNavigateToTaskDetail = navController::navigateToTaskDetailsScreen,
            onEvent = viewModel::handleIntent
        )
    }
}

private fun NavGraphBuilder.taskDetailScreen(navController: NavController, taskGraph: TasksGraph) {
    composable(
        TASKS_DETAIL_ROUTE_PATTERN,
        arguments = listOf(navArgument(TASK_ID_PARAM_KEY) { nullable = true })
    ) {
        val taskId = it.arguments?.getString(TASK_ID_PARAM_KEY)?.toLongOrNull() ?: 0
        val taskDetailsViewModelFactory = taskDetailsViewModelFactory {
            taskGraph.taskDetailsVmFactory.create(taskId)
        }
        val viewModel: TaskDetailVM = viewModel(factory = taskDetailsViewModelFactory)
        val state = viewModel.taskState.collectAsStateWithLifecycle()
        TaskDetailsScreen(
            uiState = state.value,
            onBack = navController::navigateUp,
            onEvent = viewModel::handleIntent
        )
    }
}

private fun NavController.navigateToTaskDetailsScreen(taskId: Long?) {
    navigate(TASK_DETAIL_ROUTE.plus(taskId))
}