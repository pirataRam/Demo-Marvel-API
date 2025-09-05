package com.pirataram.marveldemo.core.di

import com.pirataram.marveldemo.domain.repository.MarvelRepository
import com.pirataram.marveldemo.domain.repositoryImpl.MarvelRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepositoryImpl(
        repositoryImpl: MarvelRepositoryImpl
    ): MarvelRepository
}