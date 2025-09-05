package com.pirataram.marveldemo.domain.mappers

import com.pirataram.marveldemo.data.local.dbMarvel.entity.FavoriteEntity
import com.pirataram.marveldemo.domain.model.MarvelCharacter

fun FavoriteEntity.toModel() = MarvelCharacter(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
)

fun MarvelCharacter.toEntity() = FavoriteEntity(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
)