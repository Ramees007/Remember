package com.rms.tasks.ui

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.rms.tasks.presentation.TaskDetailVM
import com.rms.tasks.presentation.TasksViewModel

const val TASKS_GRAPH_ROUTE = "tasks"
internal const val TASK_ID_PARAM_KEY = "taskId"

private const val TASKS_LIST_ROUTE = "tasksList"
private const val TASKS_DETAIL_ROTE_PATTERN = "taskDetail?taskId={taskId}"
private const val TASK_DETAIL_ROUTE = "taskDetail?taskId="
fun NavGraphBuilder.tasksGraph(navController: NavController) {
    navigation(startDestination = TASKS_LIST_ROUTE, route = TASKS_GRAPH_ROUTE) {
        taskListScreen(navController)
        taskDetailScreen(navController)
    }
}

private fun NavGraphBuilder.taskListScreen(navController: NavController) {
    composable(TASKS_LIST_ROUTE) {
        val viewModel: TasksViewModel = hiltViewModel()
        val uiState = viewModel.flow.collectAsStateWithLifecycle()
        TasksRoute(
            uiState = uiState.value,
            onNavigateToTaskDetail = navController::navigateToTaskDetailsScreen,
            onEvent = viewModel::handleIntent
        )
    }
}

private fun NavGraphBuilder.taskDetailScreen(navController: NavController) {
    composable(
        TASKS_DETAIL_ROTE_PATTERN,
        arguments = listOf(navArgument(TASK_ID_PARAM_KEY) { nullable = true })
    ) {
        val viewModel: TaskDetailVM = hiltViewModel()
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