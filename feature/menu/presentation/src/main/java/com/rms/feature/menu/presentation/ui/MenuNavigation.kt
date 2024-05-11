package com.rms.feature.menu.presentation.ui

import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation

const val MENU_GRAPH_ROUTE = "menu"

const val MENU_LIST_ROUTE = "menu_list"

fun NavGraphBuilder.menuGraph(navController: NavController) {
    navigation(startDestination = MENU_LIST_ROUTE, route = MENU_GRAPH_ROUTE) {
        menusListScreen()
    }
}

private fun NavGraphBuilder.menusListScreen() {
    composable(MENU_LIST_ROUTE) {
        val viewModel: MenuViewModel = hiltViewModel()
        val uiState by viewModel.viewState
        MenuRoute(
            uiState = uiState,
            effectFlow = viewModel.effect,
            onEvent = viewModel::handleEvents
        )
    }
}