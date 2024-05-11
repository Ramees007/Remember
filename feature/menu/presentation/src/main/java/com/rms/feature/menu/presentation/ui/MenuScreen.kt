package com.rms.feature.menu.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow

@Composable
fun MenuRoute(
    uiState: MenuUiState,
    effectFlow: Flow<MenuUiEffect>,
    onEvent: (MenuUiEvent) -> Unit
) {
    MenuList(menus = uiState.menuItems)
}

@Composable
fun MenuList(menus: List<MenuItem>) {
    LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
        items(items = menus, { item -> item.name }) {
            MenuCard(it)
            Divider(color = Color.LightGray)
        }
    }
}

@Composable
fun MenuCard(menu: MenuItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Text(text = menu.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp))
    }
}