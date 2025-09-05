package com.pirataram.marveldemo.presentation.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pirataram.marveldemo.domain.model.MarvelCharacter
import com.pirataram.marveldemo.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {

    val favorites: StateFlow<List<MarvelCharacter>> =
        repository.getFavorites()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun removeFavorite(character: MarvelCharacter) {
        viewModelScope.launch {
            repository.removeFavorite(character)
        }
    }
}