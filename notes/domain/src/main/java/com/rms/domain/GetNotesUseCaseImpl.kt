package com.rms.domain

import com.rms.notes.data.NotesRepository
import com.rms.notes.data.model.NotesItem
import kotlinx.coroutines.flow.Flow

class GetNotesUseCaseImpl(private val notesRepository: NotesRepository) : GetNotesUseCase {

    override fun getAllNotes(): Flow<List<NotesItem>> {
        return notesRepository.getNotes()
    }
}