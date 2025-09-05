package com.pirataram.marveldemo.data.local.dbMarvel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pirataram.marveldemo.data.local.dbMarvel.dao.FavoriteDao
import com.pirataram.marveldemo.data.local.dbMarvel.entity.FavoriteEntity

@Database(
    entities = [
    FavoriteEntity::class
               ],
    exportSchema = false,
    version = 1)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDao

    companion object {
        const val MARVEL_DATABASE_NAME = "marvel_database.db"

        @Volatile
        private var INSTANCE: MarvelDatabase? = null

        fun getInstance(context: Context): MarvelDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MarvelDatabase::class.java,
                    MARVEL_DATABASE_NAME
                ).build().also { INSTANCE = it }
            }
        }
    }

}