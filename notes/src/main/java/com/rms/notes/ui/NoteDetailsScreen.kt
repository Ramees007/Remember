package com.rms.notes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rms.notes.NoteDetailViewModel

@Composable
fun NoteDetailsScreen(
    viewModel: NoteDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    val saveState = viewModel.saveState.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = saveState.value) {
        saveState.value?.let {
            onBack()
        }
    }


    Column {
        Toolbar(onBack = onBack)
        TextField(
            value = viewModel.note,
            onValueChange = { viewModel.updateNote(it) },
            modifier = Modifier.fillMaxWidth().heightIn(min = 200.dp)
        )

        Button(
            enabled = viewModel.note.text.isNotEmpty(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.scrim
            ),
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            onClick = {
                viewModel.saveNote()
            },
            shape = RectangleShape
        ) {
            Text(text = "Save", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit
) {

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
    }
}