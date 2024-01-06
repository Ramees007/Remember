package com.rms.notes.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import com.rms.domain.DeleteNoteUseCase
import com.rms.domain.GetNoteUseCase
import com.rms.domain.SaveNoteUseCase
import com.rms.notes.ui.NOTE_ID_PARAM_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<NoteDetailsUiState> =
        MutableStateFlow(
            NoteDetailsUiState(
                noteId = savedStateHandle.get<String?>(NOTE_ID_PARAM_KEY)?.toLongOrNull()
            )
        )
    val state: StateFlow<NoteDetailsUiState>
        get() = _state

    init {
        fetchNote()
    }

    fun handleIntent(intent: NoteDetailIntent) {
        when (intent) {
            NoteDetailIntent.DeleteNote -> {
                deleteNote()
            }

            is NoteDetailIntent.UpdateNote -> {
                updateNote(intent.note)
            }
        }
    }

    private fun updateNote(newNote: String) {
        _state.tryEmit(_state.value.copy(note = newNote))
        viewModelScope.launch {
            val noteId = saveNoteUseCase.saveNote(newNote, _state.value.noteId)
            _state.tryEmit(_state.value.copy(noteId = noteId))
        }
    }

    private fun deleteNote() {
        val noteId = _state.value.noteId ?: return
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(noteId)
            _state.emit(_state.value.copy(isSaved = true))
        }
    }

    private fun fetchNote() {
        viewModelScope.launch {
            val noteId = _state.value.noteId
            noteId?.let {
                val noteItem = getNoteUseCase.getNote(it)
                noteItem?.let {
                    _state.emit(_state.value.copy(note = noteItem.note, isEdit = true))
                }
            }
        }
    }
}