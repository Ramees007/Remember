package com.rms.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rms.domain.GetNotesUseCase
import com.rms.notes.presentation.NotesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val getNotesUseCase: GetNotesUseCase) : ViewModel() {

    val notes = getNotesUseCase.getAllNotes().map {
        NotesUiState.Notes(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NotesUiState.Loading
    )
}