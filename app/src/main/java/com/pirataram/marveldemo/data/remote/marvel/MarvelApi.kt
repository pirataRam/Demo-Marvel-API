package com.pirataram.marveldemo.data.remote.marvel

import com.pirataram.marveldemo.data.remote.marvel.response.MarvelResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): MarvelResponse
}