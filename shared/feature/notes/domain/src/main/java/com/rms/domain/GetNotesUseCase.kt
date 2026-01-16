package com.rms.domain

import com.rms.notes.data.model.NotesItem
import kotlinx.coroutines.flow.Flow

interface GetNotesUseCase {

    fun getAllNotes(): Flow<List<NotesItem>>
}