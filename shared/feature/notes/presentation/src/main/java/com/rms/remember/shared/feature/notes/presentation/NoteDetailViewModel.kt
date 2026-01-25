package com.rms.remember.shared.feature.notes.presentation

import androidx.lifecycle.viewModelScope
import com.rms.domain.DeleteNoteUseCase
import com.rms.domain.GetNoteUseCase
import com.rms.domain.SaveNoteUseCase
import BaseViewModel
import kotlinx.coroutines.launch

class NoteDetailViewModel(
    private val noteId: Long,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel<NoteDetailIntent, NoteDetailsUiState, NoteDetailEffect>() {

    init {
        setState { copy(noteId = this@NoteDetailViewModel.noteId) }
        fetchNote()
    }

    override fun setInitialState() = NoteDetailsUiState()

    override fun handleEvents(event: NoteDetailIntent) {
        when (event) {
            NoteDetailIntent.DeleteNote -> {
                deleteNote()
            }

            is NoteDetailIntent.UpdateNote -> {
                updateNote(event.note)
            }
        }
    }

    private fun updateNote(newNote: String) {
        setState { copy(note = newNote) }
        viewModelScope.launch {
            val noteId = saveNoteUseCase.saveNote(newNote, viewState.value.noteId)
            setState { copy(noteId = noteId) }
        }
    }

    private fun deleteNote() {
        val noteId = viewState.value.noteId ?: return
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(noteId)
            setEffect { NoteDetailEffect.NavigateBack }
        }
    }

    private fun fetchNote() {
        viewModelScope.launch {
            val noteId = viewState.value.noteId
            noteId?.let {
                val noteItem = getNoteUseCase.getNote(it)
                noteItem?.let {
                    setState { copy(note = noteItem.note, isEdit = true) }
                }
            }
        }
    }
}