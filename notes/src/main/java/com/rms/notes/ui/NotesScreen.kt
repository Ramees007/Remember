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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rms.notes.NotesUiState
import com.rms.notes.NotesViewModel
import com.rms.notes.data.model.NotesItem

@Composable
fun NotesRoute(
    vm: NotesViewModel = hiltViewModel(),
    onNavigateToNoteDetails: (id: Long) -> Unit,
    modifier: Modifier
) {
    val uiState by vm.notes.collectAsStateWithLifecycle()
    NotesScreen(
        modifier = modifier,
        notesUiState = uiState,
        onNavigateToNoteDetails = onNavigateToNoteDetails
    )
}

@Composable
fun NotesScreen(
    notesUiState: NotesUiState,
    modifier: Modifier,
    onNavigateToNoteDetails: (id: Long) -> Unit
) {
    Column(modifier = modifier) {
        when (notesUiState) {
            NotesUiState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }

            is NotesUiState.Notes -> {
                if (notesUiState.notes.isEmpty()) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "No Notes found")
                    }
                } else {
                    Notes(
                        notes = notesUiState.notes,
                        onNavigateToNoteDetails = onNavigateToNoteDetails
                    )
                }
            }
        }
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