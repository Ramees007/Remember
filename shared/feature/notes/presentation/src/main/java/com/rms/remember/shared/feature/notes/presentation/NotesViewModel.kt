package com.rms.remember.shared.feature.notes.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rms.domain.GetNotesUseCase
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Inject
class NotesViewModel(private val getNotesUseCase: GetNotesUseCase) : ViewModel() {

    val notes = getNotesUseCase.getAllNotes().map {
        NotesUiState.Notes(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = NotesUiState.Loading
    )
}