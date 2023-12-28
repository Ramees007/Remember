package com.rms.notes.di

import com.rms.db.dao.NotesDao
import com.rms.domain.GetNoteUseCase
import com.rms.domain.GetNoteUseCaseImpl
import com.rms.domain.GetNotesUseCase
import com.rms.domain.GetNotesUseCaseImpl
import com.rms.domain.SaveNoteUseCase
import com.rms.domain.SaveNoteUseCaseImpl
import com.rms.notes.data.NotesRepository
import com.rms.notes.db.NotesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NotesModule {

    @Provides
    fun provideGetNotesUseCase(notesRepository: NotesRepository): GetNotesUseCase =
        GetNotesUseCaseImpl(notesRepository)

    @Provides
    fun provideNotesRepo(notesDao: NotesDao): NotesRepository = NotesRepositoryImpl(notesDao)

    @Provides
    fun provideSaveNoteUseCase(notesRepository: NotesRepository): SaveNoteUseCase =
        SaveNoteUseCaseImpl(notesRepository)

    @Provides
    fun getNoteUseCase(notesRepository: NotesRepository): GetNoteUseCase =
        GetNoteUseCaseImpl(notesRepository)
}