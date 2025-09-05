package com.pirataram.marveldemo.domain.mappers

import com.pirataram.marveldemo.data.remote.marvel.response.Results
import com.pirataram.marveldemo.domain.model.MarvelCharacter

fun Results.toModel() = MarvelCharacter(
    id = this.id ?: 0,
    name = this.name.orEmpty(),
    imageUrl = "${this.thumbnail?.path.orEmpty()}.${this.thumbnail?.extension.orEmpty()}",
)