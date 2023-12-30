package com.rms.notes.db

import com.rms.db.dao.NotesDao
import com.rms.db.model.NotesDbItem
import com.rms.notes.data.NotesRepository
import com.rms.notes.data.model.NotesItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

class NotesRepositoryImpl(private val notesDao: NotesDao) : NotesRepository {

    override fun getNotes(): Flow<List<NotesItem>> {

        return notesDao.getNotes().mapNotNull { noteDbItem ->
            noteDbItem.map {
                NotesItem(it.uid, it.note)
            }
        }
    }

    override suspend fun saveNote(note: String, id: Long?) {
        notesDao.insertAll(NotesDbItem(id ?: 0, note))
    }

    override suspend fun getNote(noteId: Long): NotesItem? {
        return notesDao.getNote(noteId)?.let {
            NotesItem(it.uid, it.note)
        }
    }

    override suspend fun deleteNote(noteId: Long) {
        notesDao.delete(noteId)
    }
}