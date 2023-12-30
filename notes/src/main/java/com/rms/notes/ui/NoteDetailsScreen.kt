package com.rms.notes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import com.rms.notes.presentation.NoteDetailIntent
import com.rms.notes.presentation.NoteDetailsUiState

@Composable
fun NoteDetailsScreen(
    uiState: NoteDetailsUiState,
    onBack: () -> Unit,
    onEvent: (NoteDetailIntent) -> Unit
) {

    LaunchedEffect(key1 = uiState.isSaved) {
        if (uiState.isSaved) {
            onBack()
        }
    }

    Column {
        Toolbar(
            isEdit = uiState.isEdit,
            onBack = onBack,
            onDelete = {
                onEvent(NoteDetailIntent.DeleteNote)
            })
        TextField(
            value = uiState.note,
            onValueChange = { onEvent(NoteDetailIntent.UpdateNote(it)) },
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
        )

        Button(
            enabled = uiState.note.isNotEmpty(),
            colors = ButtonDefaults.textButtonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.scrim
            ),
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth(),
            onClick = {
                onEvent(NoteDetailIntent.SaveNote)
            },
            shape = RectangleShape
        ) {
            Text(text = "Save", color = MaterialTheme.colorScheme.onPrimary)
        }
    }
}

@Composable
fun Toolbar(
    isEdit: Boolean,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onDelete: () -> Unit
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

        if (isEdit) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.clickable {
                    onDelete()
                }
            )
        }
    }
}