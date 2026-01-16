package com.rms.domain

import com.rms.notes.data.NotesRepository
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.Named
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@Inject
class SaveNoteUseCaseImpl(
    private val notesRepository: NotesRepository,
    @Named("ioDispatcher")
    private val ioDispatcher: CoroutineDispatcher
) : SaveNoteUseCase {

    override suspend fun saveNote(note: String, id: Long?): Long {
        return withContext(ioDispatcher) {
            notesRepository.saveNote(note, id)
        }
    }
}