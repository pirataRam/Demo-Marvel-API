package com.pirataram.marveldemo.presentation.ui.favorites

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.pirataram.marveldemo.presentation.ui.common.CharacterItem

@Composable
fun FavoritesScreen() {
    val viewModel: FavoritesViewModel = hiltViewModel()
    val favorites by viewModel.favorites.collectAsState()

    LazyColumn {
        items(favorites) { character ->
            CharacterItem(character, true, onFavoriteClick = {
                viewModel.removeFavorite(character)
            })
        }
    }
}