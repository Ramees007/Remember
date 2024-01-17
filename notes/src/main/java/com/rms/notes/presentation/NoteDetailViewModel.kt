package com.rms.notes.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import com.rms.domain.DeleteNoteUseCase
import com.rms.domain.GetNoteUseCase
import com.rms.domain.SaveNoteUseCase
import com.rms.notes.ui.NOTE_ID_PARAM_KEY
import com.rms.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel<NoteDetailIntent, NoteDetailsUiState, NoteDetailEffect>() {

    init {
        setState { copy(noteId = savedStateHandle.get<String?>(NOTE_ID_PARAM_KEY)?.toLongOrNull()) }
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