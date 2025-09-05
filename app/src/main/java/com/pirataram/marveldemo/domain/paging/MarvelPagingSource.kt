package com.pirataram.marveldemo.domain.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pirataram.marveldemo.BuildConfig
import com.pirataram.marveldemo.data.remote.marvel.MarvelApi
import com.pirataram.marveldemo.domain.mappers.toModel
import com.pirataram.marveldemo.domain.model.MarvelCharacter

class MarvelPagingSource (
    private val api: MarvelApi
) : PagingSource<Int, MarvelCharacter>() {

    override fun getRefreshKey(state: PagingState<Int, MarvelCharacter>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MarvelCharacter> {
        val offset = params.key ?: 0
        return try {
            val response = api.getCharacters(offset = offset, limit = params.loadSize)
            val characters = response.data?.results?.map { it.toModel()} ?: emptyList()
            val nextKey = if (characters.isEmpty()) {
                null
            } else {
                offset + params.loadSize
            }
            LoadResult.Page(
                data = characters,
                prevKey = if (offset == 0) null else offset - params.loadSize,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}