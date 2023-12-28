package com.rms.domain

import com.rms.notes.data.NotesRepository

class DeleteNoteUseCaseImpl(private val notesRepository: NotesRepository) : DeleteNoteUseCase {

    override suspend fun deleteNote(id: Long) {
        notesRepository.deleteNote(id)
    }
}