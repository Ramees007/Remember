package com.rms.domain

import com.rms.notes.data.NotesRepository
import com.rms.notes.data.model.NotesItem

class GetNoteUseCaseImpl (private val notesRepository: NotesRepository): GetNoteUseCase {

    override suspend fun getNote(noteId: Long): NotesItem? {
        return notesRepository.getNote(noteId)
    }
}