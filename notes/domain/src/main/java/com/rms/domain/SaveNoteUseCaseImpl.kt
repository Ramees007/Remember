package com.rms.domain

import com.rms.notes.data.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SaveNoteUseCaseImpl(private val notesRepository: NotesRepository) : SaveNoteUseCase {

    override suspend fun saveNote(note: String, id: Long?) {
        withContext(Dispatchers.IO) {
            notesRepository.saveNote(note, id)
        }
    }
}