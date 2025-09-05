package com.pirataram.marveldemo.core.di

import android.content.Context
import androidx.room.Room
import com.pirataram.marveldemo.data.local.dbMarvel.MarvelDatabase
import com.pirataram.marveldemo.data.local.dbMarvel.MarvelDatabase.Companion.MARVEL_DATABASE_NAME
import com.pirataram.marveldemo.data.local.dbMarvel.dao.FavoriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabaseMarvel(@ApplicationContext context: Context):
            MarvelDatabase = Room.databaseBuilder(context, MarvelDatabase::class.java, MARVEL_DATABASE_NAME).build()

    @Provides
    fun provideFavoriteDaoMarvel(db: MarvelDatabase): FavoriteDao = db.favoriteDao()
}