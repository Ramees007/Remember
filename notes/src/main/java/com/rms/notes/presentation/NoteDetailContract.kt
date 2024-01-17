package com.rms.notes.presentation

import com.rms.ui.base.ViewEvent
import com.rms.ui.base.ViewSideEffect
import com.rms.ui.base.ViewState

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