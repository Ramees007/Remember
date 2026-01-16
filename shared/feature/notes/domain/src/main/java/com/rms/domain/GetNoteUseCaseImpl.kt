package com.rms.domain

import com.rms.notes.data.NotesRepository
import com.rms.notes.data.model.NotesItem
import dev.zacsweers.metro.Inject

@Inject
class GetNoteUseCaseImpl (private val notesRepository: NotesRepository): GetNoteUseCase {

    override suspend fun getNote(noteId: Long): NotesItem? {
        return notesRepository.getNote(noteId)
    }
}