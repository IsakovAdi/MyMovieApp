package com.example.mymovieapp.data.storage.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mymovieapp.data.storage.GenreIdsConverter
import com.example.mymovieapp.data.storage.model.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
@TypeConverters(GenreIdsConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieItemDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "movie_item.db"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = db
                return db
            }
        }
    }

}