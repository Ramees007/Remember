package com.rms.domain

import com.rms.notes.data.NotesRepository
import dev.zacsweers.metro.Inject

@Inject
class DeleteNoteUseCaseImpl(private val notesRepository: NotesRepository) : DeleteNoteUseCase {

    override suspend fun deleteNote(id: Long) {
        notesRepository.deleteNote(id)
    }
}