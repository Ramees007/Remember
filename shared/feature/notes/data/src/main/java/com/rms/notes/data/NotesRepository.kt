package com.rms.notes.data

import com.rms.notes.data.model.NotesItem
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getNotes(): Flow<List<NotesItem>>

    suspend fun saveNote(note: String, id: Long?): Long

    suspend fun getNote(noteId: Long): NotesItem?

    suspend fun deleteNote(noteId: Long)
}