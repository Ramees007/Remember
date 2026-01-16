package com.rms.domain

import com.rms.notes.data.NotesRepository
import com.rms.notes.data.model.NotesItem
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.Flow

@Inject
class GetNotesUseCaseImpl(private val notesRepository: NotesRepository) : GetNotesUseCase {

    override fun getAllNotes(): Flow<List<NotesItem>> {
        return notesRepository.getNotes()
    }
}