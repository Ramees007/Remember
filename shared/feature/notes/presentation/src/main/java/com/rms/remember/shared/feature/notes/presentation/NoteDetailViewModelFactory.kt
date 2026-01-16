package com.rms.remember.shared.feature.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rms.domain.DeleteNoteUseCase
import com.rms.domain.GetNoteUseCase
import com.rms.domain.SaveNoteUseCase
import dev.zacsweers.metro.Assisted
import dev.zacsweers.metro.AssistedFactory
import dev.zacsweers.metro.AssistedInject

@AssistedInject
class NoteDetailViewModelFactory(
    @Assisted
    private val noteId: Long,
    private val saveNoteUseCase: SaveNoteUseCase,
    private val getNoteUseCase: GetNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteDetailViewModel::class.java)) {
            return NoteDetailViewModel(
                noteId = noteId,
                saveNoteUseCase = saveNoteUseCase,
                getNoteUseCase = getNoteUseCase,
                deleteNoteUseCase = deleteNoteUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }

    @AssistedFactory
    fun interface Factory {
        fun create(noteId: Long): NoteDetailViewModelFactory
    }
}