package com.rms.notes.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rms.notes.presentation.NotesUiState
import com.rms.notes.data.model.NotesItem

@Composable
fun NotesRoute(
    modifier: Modifier = Modifier,
    uiState: NotesUiState,
    onNavigateToNoteDetails: (id: Long?) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigateToNoteDetails(null)
            }) {
                Icon(Icons.Filled.Add, "Add")
            }
        }) { padding ->
        NotesScreen(
            modifier = modifier.padding(padding),
            notesUiState = uiState,
            onNavigateToNoteDetails = { noteId ->
                onNavigateToNoteDetails(noteId)
            }
        )
    }
}

@Composable
fun NotesScreen(
    modifier: Modifier,
    notesUiState: NotesUiState,
    onNavigateToNoteDetails: (id: Long) -> Unit
) {
    when (notesUiState) {
        NotesUiState.Loading -> {
            Loader(modifier)
        }

        is NotesUiState.Notes -> {
            NotesWrapper(
                modifier = modifier,
                notes = notesUiState.notes,
                onNavigateToNoteDetails = onNavigateToNoteDetails
            )
        }
    }
}

@Composable
fun Loader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun NotesWrapper(
    modifier: Modifier = Modifier,
    notes: List<NotesItem>,
    onNavigateToNoteDetails: (Long) -> Unit
) {
    if (notes.isEmpty()) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No Notes found")
        }
    } else {
        Notes(
            notes = notes,
            onNavigateToNoteDetails = onNavigateToNoteDetails
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Notes(notes: List<NotesItem>, onNavigateToNoteDetails: (id: Long) -> Unit) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
        modifier = Modifier.padding(all = 8.dp)
    ) {
        items(count = notes.size, key = { notes[it].id }) {
            Note(notes[it], onNavigateToNoteDetails)
        }
    }
}

@Composable
fun Note(note: NotesItem, onNavigateToNoteDetails: (id: Long) -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .clickable {
                onNavigateToNoteDetails(note.id)
            },
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Text(
            text = note.note,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        )
    }
}