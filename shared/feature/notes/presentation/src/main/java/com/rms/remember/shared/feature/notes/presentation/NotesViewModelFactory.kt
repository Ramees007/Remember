package com.rms.remember.shared.feature.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rms.domain.GetNotesUseCase

class NotesViewModelFactory(private val getNotesUseCase: GetNotesUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(getNotesUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }

    fun interface Factory {
        fun create(): NotesViewModelFactory
    }
}