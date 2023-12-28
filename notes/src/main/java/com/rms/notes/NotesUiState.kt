package com.rms.notes

import com.rms.notes.data.model.NotesItem

sealed interface NotesUiState {

    object Loading: NotesUiState

    data class Notes(val notes: List<NotesItem>): NotesUiState
}