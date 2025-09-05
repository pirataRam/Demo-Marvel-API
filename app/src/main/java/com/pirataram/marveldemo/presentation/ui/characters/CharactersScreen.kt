package com.pirataram.marveldemo.presentation.ui.characters

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pirataram.marveldemo.presentation.ui.common.CharacterItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharactersScreen(onNavigateToFavorites: () -> Unit) {
    val viewModel: CharactersViewModel = hiltViewModel()
    val characters = viewModel.characters.collectAsLazyPagingItems()
    val context = LocalContext.current


    Scaffold(
        topBar = { TopAppBar(title = { Text("Personajes Marvel") }, actions = {
            IconButton(onClick = onNavigateToFavorites) {
                Icon(Icons.Default.Favorite, contentDescription = "Favoritos")
            }
        }) }
    ) {
        LazyColumn(contentPadding = it) {
            items(characters.itemCount) { index ->
                characters[index]?.let { character ->
                    CharacterItem(character, false, onFavoriteClick = {
                        viewModel.addCharacterToFavorites(character)
                        Toast.makeText(context, "Agregado a favoritos", Toast.LENGTH_SHORT).show()
                    })
                }
            }
            when (characters.loadState.append) {
                is LoadState.Loading -> item { CircularProgressIndicator(modifier = Modifier.fillMaxWidth().padding(16.dp)) }
                is LoadState.Error -> item { Text("Error al cargar más") }
                else -> {}
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CharactersScreenPreview() {
    CharactersScreen(onNavigateToFavorites = {})
}