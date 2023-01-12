package com.rms.tasks.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.rms.tasks.presentation.TasksViewModel


@Composable
fun TaskDetailsScreen(vm: TasksViewModel = hiltViewModel()) {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    Column {
        TextField(
            modifier = Modifier
                .fillMaxWidth(),
            textState
        )
        Button(onClick = {
            vm.save(textState.value.text)
        }) {
            Text(text = "Save")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    modifier: Modifier,
    textState: MutableState<TextFieldValue> = remember { mutableStateOf(TextFieldValue()) }
) {

    TextField(modifier = modifier, value = textState.value, onValueChange = {
        textState.value = it
    })
}