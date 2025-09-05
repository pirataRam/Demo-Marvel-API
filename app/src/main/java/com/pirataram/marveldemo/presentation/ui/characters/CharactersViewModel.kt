package com.pirataram.marveldemo.presentation.ui.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pirataram.marveldemo.domain.model.MarvelCharacter
import com.pirataram.marveldemo.domain.repository.MarvelRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: MarvelRepository
) : ViewModel() {

    val characters: Flow<PagingData<MarvelCharacter>> =
        repository.getCharacters()
            .cachedIn(viewModelScope)

    fun addCharacterToFavorites(character: MarvelCharacter) {
        viewModelScope.launch {
            repository.addFavorite(character)
        }
    }
}