package com.rms.remember.shared.feature.notes.presentation.di

import com.rms.db.AppDataBase
import com.rms.db.dao.NotesDao
import com.rms.db.dao.TaskDao
import com.rms.domain.DeleteNoteUseCase
import com.rms.domain.DeleteNoteUseCaseImpl
import com.rms.domain.GetNoteUseCase
import com.rms.domain.GetNoteUseCaseImpl
import com.rms.domain.SaveNoteUseCase
import com.rms.domain.SaveNoteUseCaseImpl
import com.rms.remember.shared.feature.notes.presentation.NoteDetailViewModelFactory
import com.rms.remember.shared.feature.notes.presentation.NotesViewModel
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.GraphExtension
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.Scope

@GraphExtension(NotesScope::class)
interface NotesGraph {

    val notesViewModel: NotesViewModel

    val noteDetailsViewModelFactoryFactory: NoteDetailViewModelFactory.Factory

    @Binds
    val GetNoteUseCaseImpl.Bind: GetNoteUseCase

    @Binds
    val SaveNoteUseCaseImpl.Bind: SaveNoteUseCase

    @Binds
    val DeleteNoteUseCaseImpl.Bind: DeleteNoteUseCase

    @Provides
    fun taskDao(db: AppDataBase): NotesDao = db.notestDao()

    @ContributesTo(AppScope::class)
    @GraphExtension.Factory
    interface Factory {
        fun createNotesGraph(): NotesGraph
    }
}

@Scope
annotation class NotesScope