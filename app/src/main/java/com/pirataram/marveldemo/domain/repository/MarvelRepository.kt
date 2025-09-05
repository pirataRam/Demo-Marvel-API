package com.pirataram.marveldemo.domain.repository

import androidx.paging.PagingData
import com.pirataram.marveldemo.domain.model.MarvelCharacter
import kotlinx.coroutines.flow.Flow

interface MarvelRepository {
    fun getCharacters(): Flow<PagingData<MarvelCharacter>>
    suspend fun addFavorite(character: MarvelCharacter)
    suspend fun removeFavorite(character: MarvelCharacter)
    fun getFavorites(): Flow<List<MarvelCharacter>>
}