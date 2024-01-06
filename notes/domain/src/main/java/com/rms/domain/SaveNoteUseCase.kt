package com.rms.domain

interface SaveNoteUseCase {

    suspend fun saveNote(note: String, id: Long? = null): Long
}