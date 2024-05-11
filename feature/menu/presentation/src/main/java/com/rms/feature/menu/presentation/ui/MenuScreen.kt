package com.rms.feature.menu.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

@Composable
fun MenuRoute(uiState: MenuUiState,
              effectFlow: Flow<MenuUiEffect>,
              onEvent: (MenuUiEvent) -> Unit) {

    Text("Menu")
}