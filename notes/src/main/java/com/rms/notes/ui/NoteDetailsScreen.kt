package com.rms.notes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rms.notes.presentation.NoteDetailEffect
import com.rms.notes.presentation.NoteDetailIntent
import com.rms.notes.presentation.NoteDetailsUiState
import com.rms.ui.base.SIDE_EFFECTS_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun NoteDetailsScreen(
    uiState: NoteDetailsUiState,
    effectFlow: Flow<NoteDetailEffect>,
    onBack: () -> Unit,
    onEvent: (NoteDetailIntent) -> Unit
) {

    LaunchedEffect(key1 = SIDE_EFFECTS_KEY) {
        effectFlow.onEach { effect ->
            when (effect) {
                NoteDetailEffect.NavigateBack -> {
                    onBack()
                }
            }
        }.collect()
    }

    Scaffold(
        topBar = {
            TopBar(
                uiState = uiState,
                onBack = onBack,
                onEvent = onEvent
            )
        })
    {
        Column(modifier = Modifier.padding(it)) {
            TextField(
                value = uiState.note,
                onValueChange = { onEvent(NoteDetailIntent.UpdateNote(it)) },
                modifier = Modifier
                    .fillMaxSize(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    uiState: NoteDetailsUiState,
    onBack: () -> Unit,
    onEvent: (NoteDetailIntent) -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        title = {
            Text(if (uiState.isEdit) "Edit Note" else "Add Note")
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        actions = {
            if (uiState.isEdit) {
                Icon(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable {
                            onEvent(NoteDetailIntent.DeleteNote)
                        },
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    )
}
