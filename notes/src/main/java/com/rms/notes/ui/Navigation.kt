package com.rms.notes.ui

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.rms.notes.presentation.NoteDetailViewModel
import com.rms.notes.presentation.NotesViewModel

const val NOTES_GRAPH_ROUTE = "notes"
internal const val NOTE_ID_PARAM_KEY = "noteId"

private const val NOTES_LIST_ROUTE = "notesList"
private const val NOTES_DETAIL_ROUTE_PATTERN = "noteDetail?noteId={noteId}"
private const val NOTES_DETAIL_ROUTE = "noteDetail?noteId="


fun NavGraphBuilder.notesGraph(navController: NavController) {
    navigation(startDestination = NOTES_LIST_ROUTE, route = NOTES_GRAPH_ROUTE) {
        notesListScreen(navController)
        noteDetailsScreen(navController)
    }
}

private fun NavGraphBuilder.notesListScreen(navController: NavController) {
    composable(NOTES_LIST_ROUTE) {
        val vm: NotesViewModel = hiltViewModel()
        val uiState by vm.notes.collectAsStateWithLifecycle()
        NotesRoute(
            uiState = uiState,
            onNavigateToNoteDetails = navController::navigateToNoteDetails
        )
    }
}

private fun NavGraphBuilder.noteDetailsScreen(navController: NavController) {
    composable(
        NOTES_DETAIL_ROUTE_PATTERN,
        arguments = listOf(navArgument(NOTE_ID_PARAM_KEY) { nullable = true })
    ) {
        val viewModel: NoteDetailViewModel = hiltViewModel()
        val uiState by viewModel.viewState
        NoteDetailsScreen(
            uiState = uiState,
            effectFlow = viewModel.effect,
            onBack = navController::navigateUp,
            onEvent = viewModel::setEvent
        )
    }
}

private fun NavController.navigateToNoteDetails(noteId: Long?) {
    navigate(NOTES_DETAIL_ROUTE.plus(noteId))
}