package com.rms.domain

interface DeleteNoteUseCase {

    suspend fun deleteNote(id: Long)
}