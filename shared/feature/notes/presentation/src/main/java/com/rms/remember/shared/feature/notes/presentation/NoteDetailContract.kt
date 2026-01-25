package com.rms.remember.shared.feature.notes.presentation

import ViewEvent
import ViewSideEffect
import ViewState

data class NoteDetailsUiState(
    val isEdit: Boolean = false,
    val note: String = "",
    val noteId: Long? = null
) : ViewState

sealed interface NoteDetailIntent : ViewEvent {

    object DeleteNote : NoteDetailIntent

    data class UpdateNote(val note: String) : NoteDetailIntent

}

sealed interface NoteDetailEffect : ViewSideEffect {

    object NavigateBack : NoteDetailEffect
}