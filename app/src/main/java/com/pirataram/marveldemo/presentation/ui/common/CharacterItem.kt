package com.pirataram.marveldemo.presentation.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pirataram.marveldemo.domain.model.MarvelCharacter

@Composable
fun CharacterItem(
    character: MarvelCharacter,
    isFavorite: Boolean = false,
    onFavoriteClick: (MarvelCharacter) -> Unit = {}
) {

    var showDialog by remember { mutableStateOf(false) }
    var isFav by remember { mutableStateOf(isFavorite) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del personaje
            AsyncImage(
                model = character.imageUrl,
                contentDescription = character.name,
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                onClick = {
                    showDialog = true
                }
            )

            Spacer(modifier = Modifier.width(12.dp))

            // Nombre del personaje
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f)
            )

            // Botón de favorito
            IconButton(onClick = {
                isFav = !isFav
                onFavoriteClick(character) }) {
                Icon(
                    imageVector = if (isFav) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorito",
                    tint = if (isFav) Color.Red else Color.Gray
                )
            }
        }
    }

    if (showDialog) {
        ImageViewerDialog(character.imageUrl, true) { showDialog = false }
    }
}

@Preview
@Composable
fun CharacterItemPreview() {
    val character = MarvelCharacter(
        id = 1,
        name = "Spider-Man",
        imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
    )
    CharacterItem(character = character)
}

@Preview
@Composable
fun CharacterItemFavoritePreview() {
    val character = MarvelCharacter(id = 1, name = "Iron Man", imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg")
    CharacterItem(character = character, isFavorite = true)
}