package com.rms.domain

import com.rms.notes.data.NotesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SaveNoteUseCaseImpl(
    private val notesRepository: NotesRepository,
    private val ioDispatcher: CoroutineDispatcher
) : SaveNoteUseCase {

    override suspend fun saveNote(note: String, id: Long?): Long {
        return withContext(ioDispatcher) {
            notesRepository.saveNote(note, id)
        }
    }
}