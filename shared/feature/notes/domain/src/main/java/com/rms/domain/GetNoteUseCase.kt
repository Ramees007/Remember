package com.rms.domain

import com.rms.notes.data.model.NotesItem

interface GetNoteUseCase {

    suspend fun getNote(noteId: Long): NotesItem?
}