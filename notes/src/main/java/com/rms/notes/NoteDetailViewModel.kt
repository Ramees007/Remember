package com.rms.notes

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import com.rms.domain.GetNoteUseCase
import com.rms.domain.SaveNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(SavedStateHandleSaveableApi::class)
@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase
) : ViewModel() {

    private val noteId: Long? = savedStateHandle.get<String?>("noteId")?.toLongOrNull()

    var note by savedStateHandle.saveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
        private set

    private val _saveState: MutableStateFlow<Unit?> = MutableStateFlow(null)
    val saveState: StateFlow<Unit?>
        get() = _saveState

    init {
        fetchNote()
    }

    fun updateNote(newNote: TextFieldValue) {
        note = newNote
    }

    fun saveNote() {
        viewModelScope.launch {
            saveNoteUseCase.saveNote(note.text, noteId)
            _saveState.emit(Unit)
        }
    }

    private fun fetchNote() {
        viewModelScope.launch {
            noteId?.let {
                val noteItem = getNoteUseCase.getNote(it)
                noteItem?.let {
                    note = TextFieldValue(noteItem.note)
                }
            }
        }
    }
}