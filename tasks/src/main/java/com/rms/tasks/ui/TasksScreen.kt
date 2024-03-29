package com.rms.tasks.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rms.tasks.model.TaskItem
import com.rms.tasks.model.TaskStatus
import com.rms.tasks.presentation.TasksUiIntent
import com.rms.tasks.presentation.TasksUiState
import com.rms.ui.theme.LightGreen
import com.rms.ui.theme.LightRed


@Composable
fun TasksRoute(
    modifier: Modifier = Modifier,
    uiState: TasksUiState,
    onNavigateToTaskDetail: (taskId: Long?) -> Unit,
    onEvent: (TasksUiIntent) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigateToTaskDetail(null)
            }) {
                Icon(Icons.Filled.Add, "Add")
            }
        }) { padding ->
        TasksScreen(
            modifier = modifier.padding(padding),
            uiState = uiState,
            onCheckedChanged = { id, selected ->
                onEvent(TasksUiIntent.Done(id, selected))
            },
            onNavigateToTaskDetail = {
                onNavigateToTaskDetail(it)
            },
            onDeleteTask = {
                onEvent(TasksUiIntent.Delete(it))
            }
        )
    }
}

@Composable
fun TasksScreen(
    modifier: Modifier,
    uiState: TasksUiState,
    onCheckedChanged: (Long, Boolean) -> Unit,
    onNavigateToTaskDetail: (taskId: Long) -> Unit,
    onDeleteTask: (Long) -> Unit
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
                TasksList(
                    tasks = uiState.tasks,
                    onCheckedChanged = onCheckedChanged,
                    onNavigateToTaskDetail = onNavigateToTaskDetail,
                    onDeleteTask = onDeleteTask
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksList(
    tasks: List<TaskItem>,
    onCheckedChanged: (Long, Boolean) -> Unit,
    onNavigateToTaskDetail: (taskId: Long) -> Unit,
    onDeleteTask: (Long) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
        items(items = tasks, { item -> item.id }) { task ->
            val dismissState = rememberDismissState()
            if (dismissState.isDismissed(DismissDirection.StartToEnd)) {
                onDeleteTask(task.id)
            }
            SwipeToDismiss(
                state = dismissState,
                background = {
                    DismissBg(dismissState = dismissState)
                },
                directions = setOf(
                    DismissDirection.StartToEnd
                ),
                dismissContent = {
                    TaskItem(
                        task = task,
                        onCheckedChanged = onCheckedChanged,
                        onNavigateToTaskDetail = onNavigateToTaskDetail
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBg(dismissState: DismissState) {
    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.White
            else -> Color.Red
        }, label = "bg_color_anim"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 2.dp)
            .clip(shape = RoundedCornerShape(4.dp))
            .background(color)
    )
}

@Composable
fun TaskItem(
    task: TaskItem, onCheckedChanged: (Long, Boolean) -> Unit,
    onNavigateToTaskDetail: (taskId: Long) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 2.dp),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = task.getContainerColor()
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier
                .weight(1f)
                .clickable {
                    onNavigateToTaskDetail(task.id)
                }) {
                Text(
                    text = task.task,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 4.dp)
                )
                if (task.date.isNotEmpty()) {
                    Text(
                        text = task.date,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .padding(start = 8.dp, top = 0.dp, end = 8.dp, bottom = 8.dp)
                    )
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

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

private fun TaskItem.getContainerColor(): Color {
    return when (status) {
        TaskStatus.PastUnDone -> LightRed
        TaskStatus.TodaysUnDone -> LightGreen
        else -> Color.White
    }
}