package com.rms.tasks.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rms.tasks.presentation.TaskDetailVM
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate


@Composable
fun TaskDetailsScreen(
    vm: TaskDetailVM = hiltViewModel(),
    onBack: () -> Unit
) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Column {
        Toolbar(
            onBack = onBack,
            onDateSet = {
                vm.setTime(it)
            }
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            textState
        )
        Spacer(modifier = Modifier.weight(1f))
        val context = LocalContext.current
        Text(
            text = "Save",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp)
                .clickable {
                    val txt = textState.value.text
                    if (txt.isEmpty()) {
                        Toast
                            .makeText(context, "Enter task", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        vm.save(txt)
                    }

                })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    modifier: Modifier,
    textState: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) }
) {

    TextField(modifier = modifier.height(100.dp), value = textState.value, onValueChange = {
        textState.value = it
    })
}

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
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
    }

    DateTimePickerDialog(dialogState, onDateSet)
}

@Composable
fun DateTimePickerDialog(
    dialogState: MaterialDialogState,
    onDateSet: (LocalDate) -> Unit
) {
    MaterialDialog(
        dialogState = dialogState,
        buttons = {
            positiveButton("Ok")
            negativeButton("Cancel")
        }
    ) {
        datepicker { date ->
            onDateSet(date)
        }
    }
}