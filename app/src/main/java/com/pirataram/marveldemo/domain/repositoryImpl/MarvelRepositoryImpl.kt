package com.pirataram.marveldemo.domain.repositoryImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pirataram.marveldemo.data.local.dbMarvel.dao.FavoriteDao
import com.pirataram.marveldemo.data.remote.marvel.MarvelApi
import com.pirataram.marveldemo.domain.mappers.toEntity
import com.pirataram.marveldemo.domain.mappers.toModel
import com.pirataram.marveldemo.domain.model.MarvelCharacter
import com.pirataram.marveldemo.domain.paging.MarvelPagingSource
import com.pirataram.marveldemo.domain.repository.MarvelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class MarvelRepositoryImpl @Inject constructor(
    private val api: MarvelApi,
    private val dao: FavoriteDao
): MarvelRepository {
    override fun getCharacters(): Flow<PagingData<MarvelCharacter>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { MarvelPagingSource(api) }
        ).flow
    }

    override suspend fun addFavorite(character: MarvelCharacter) =
        dao.insertFavorite(character.toEntity())

    override suspend fun removeFavorite(character: MarvelCharacter) =
        dao.deleteFavorite(character.toEntity())

    override fun getFavorites(): Flow<List<MarvelCharacter>> =
        dao.getAllFavorites().map { list -> list.map { it.toModel() } }
}