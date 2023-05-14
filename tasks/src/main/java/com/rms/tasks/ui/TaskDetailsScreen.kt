package com.rms.tasks.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rms.android_util.DateFormat
import com.rms.android_util.getCurrentLocalDate
import com.rms.android_util.toLocalDate
import com.rms.tasks.presentation.TaskDetailVM
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate


@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun TaskDetailsScreen(
    vm: TaskDetailVM = hiltViewModel(),
    onBack: () -> Unit
) {
    val dateState = vm.taskDate.collectAsStateWithLifecycle()
    val text = vm.taskDetailText
    val saveState = vm.saveState.collectAsStateWithLifecycle()

    if (saveState.value != null) {
        LaunchedEffect(key1 = saveState.value) {
            onBack()
        }
    }

    Column {
        Toolbar(
            onBack = onBack,
            dateState = dateState,
            onDateSet = {
                vm.setTime(it)
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            text = text,
            onUpdateTask = {
                vm.updateText(it)
            }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            enabled = text.isNotEmpty(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.scrim
            ),
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            onClick = {
                vm.save()
            },
            shape = RectangleShape
        ) {
            Text(text = "Save", color = MaterialTheme.colorScheme.onPrimary)
        }
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
    dateState: State<String?>,
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
        val date = dateState.value
        if (date.isNullOrEmpty()) {
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
            Text(text = date, modifier = Modifier.clickable {
                dialogState.show()
            })
        }
    }

    DateTimePickerDialog(dialogState, dateState, onDateSet)
}

@Composable
fun DateTimePickerDialog(
    dialogState: MaterialDialogState,
    dateState: State<String?>,
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
            initialDate = dateState.value?.takeIf { it.isNotEmpty() }
                ?.toLocalDate(DateFormat.DD_MMM_YY)
                ?: getCurrentLocalDate()
        ) { date ->
            onDateSet(date)
        }
    }
}