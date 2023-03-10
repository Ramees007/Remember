package com.rms.tasks.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rms.data.TaskItem
import com.rms.tasks.presentation.TasksUiState
import com.rms.tasks.presentation.TasksViewModel

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TasksRoute(vm: TasksViewModel = hiltViewModel(), modifier: Modifier) {
    val uiState by vm.flow.collectAsStateWithLifecycle()
    TasksScreen(
        uiState = uiState,
        modifier = modifier,
        onCheckedChanged = vm::onCheckedChanged
    )
}

@Composable
fun TasksScreen(
    uiState: TasksUiState,
    onCheckedChanged: (Long, Boolean) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
    ) {
        when (uiState) {
            TasksUiState.Empty -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "No items found")
                }
            }

            TasksUiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is TasksUiState.Tasks -> {
                TasksList(tasks = uiState.tasks, onCheckedChanged = onCheckedChanged)
            }
        }
    }
}

@Composable
fun TasksList(tasks: List<TaskItem>, onCheckedChanged: (Long, Boolean) -> Unit) {
    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
        items(items = tasks) { task ->
            TaskItem(task = task, onCheckedChanged = onCheckedChanged)
        }
    }
}

@Composable
fun TaskItem(task: TaskItem, onCheckedChanged: (Long, Boolean) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = task.task,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1F)
            )
            RoundCheckBox(
                isChecked = task.isDone,
                onCheckedChanged = { onCheckedChanged(task.id, it) })
        }
    }
}

@Composable
fun RoundCheckBox(isChecked: Boolean, onCheckedChanged: (Boolean) -> Unit) {
    IconButton(onClick = { onCheckedChanged(!isChecked) }) {
        if (isChecked) {
            Icon(
                imageVector = Icons.Outlined.CheckCircle,
                tint = MaterialTheme.colorScheme.primary,
                contentDescription = "",
                modifier = Modifier.size(28.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = CircleShape
                    )
                    .size(23.dp)
            )
        }
    }
}