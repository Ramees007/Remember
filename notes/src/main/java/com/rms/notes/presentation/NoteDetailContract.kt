package com.rms.notes.presentation

data class NoteDetailsUiState(
    val isSaved: Boolean = false,
    val isEdit: Boolean = false,
    val note: String = "",
    val noteId: Long? = null
)

sealed interface NoteDetailIntent {

    object DeleteNote : NoteDetailIntent

    data class UpdateNote(val note: String) : NoteDetailIntent

}