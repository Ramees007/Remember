package com.rms.notes.ui

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.rms.remember.shared.feature.notes.presentation.NoteDetailViewModel
import com.rms.remember.shared.feature.notes.presentation.NotesViewModel
import com.rms.remember.shared.feature.notes.presentation.di.NotesGraph

const val NOTES_GRAPH_ROUTE = "notes"
internal const val NOTE_ID_PARAM_KEY = "noteId"

private const val NOTES_LIST_ROUTE = "notesList"
private const val NOTES_DETAIL_ROUTE_PATTERN = "noteDetail?noteId={noteId}"
private const val NOTES_DETAIL_ROUTE = "noteDetail?noteId="


fun NavGraphBuilder.notesGraph(navController: NavController, notesGraph: NotesGraph) {
    navigation(startDestination = NOTES_LIST_ROUTE, route = NOTES_GRAPH_ROUTE) {
        notesListScreen(navController, notesGraph)
        noteDetailsScreen(navController, notesGraph)
    }
}

private fun NavGraphBuilder.notesListScreen(navController: NavController, notesGraph: NotesGraph) {
    composable(NOTES_LIST_ROUTE) {
        val vm: NotesViewModel = notesGraph.notesViewModel
        val uiState by vm.notes.collectAsStateWithLifecycle()
        NotesRoute(
            uiState = uiState,
            onNavigateToNoteDetails = navController::navigateToNoteDetails
        )
    }
}

private fun NavGraphBuilder.noteDetailsScreen(
    navController: NavController,
    notesGraph: NotesGraph
) {
    composable(
        NOTES_DETAIL_ROUTE_PATTERN,
        arguments = listOf(navArgument(NOTE_ID_PARAM_KEY) { nullable = true })
    ) {
        val viewModel: NoteDetailViewModel =
            viewModel(factory = notesGraph.noteDetailsViewModelFactoryFactory.create(1))

        val uiState by viewModel.viewState.collectAsStateWithLifecycle()
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