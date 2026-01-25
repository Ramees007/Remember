package com.rms.tasks.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import presentation.TaskDetailIntent
import presentation.TaskDetailUiState
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import getCurrentJavaLocalDate
import kotlinx.datetime.LocalDate
import toJavaLocalDate
import toKmp

@Composable
fun TaskDetailsScreen(
    onBack: () -> Unit,
    uiState: TaskDetailUiState,
    onEvent: (TaskDetailIntent) -> Unit
) {

    Column {
        Toolbar(
            onBack = onBack,
            dateStr = uiState.date,
            onDateSet = {
                onEvent(TaskDetailIntent.SetDate(it))
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = uiState.taskStr,
            onUpdateTask = {
                onEvent(TaskDetailIntent.UpdateTask(it))
            }
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    modifier: Modifier,
    text: String,
    onUpdateTask: (txt: String) -> Unit
) {

    TextField(modifier = modifier.height(100.dp), value = text, onValueChange = {
        onUpdateTask(it)
    })
}

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    dateStr: String?,
    onDateSet: (LocalDate) -> Unit
) {
    val dialogState = rememberMaterialDialogState()
    Row(modifier = modifier.padding(16.dp)) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = "",
            modifier = Modifier
                .size(28.dp)
                .clickable {
                    onBack()
                }
        )

        Spacer(modifier = Modifier.weight(1f))
        if (dateStr.isNullOrEmpty()) {
            Icon(
                imageVector = Icons.Outlined.DateRange,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = "",
                modifier = Modifier
                    .size(28.dp)
                    .clickable {
                        dialogState.show()
                    }
            )
        } else {
            Text(text = dateStr, modifier = Modifier.clickable {
                dialogState.show()
            })
        }
    }

    DateTimePickerDialog(dialogState, dateStr, onDateSet)
}

@Composable
fun DateTimePickerDialog(
    dialogState: MaterialDialogState,
    dateStr: String?,
    onDateSet: (LocalDate) -> Unit
) {

    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker(
            initialDate = dateStr?.takeIf { it.isNotEmpty() }
                ?.toJavaLocalDate()
                ?: getCurrentJavaLocalDate()
        ) { date ->
            onDateSet(date.toKmp())
        }
    }
}