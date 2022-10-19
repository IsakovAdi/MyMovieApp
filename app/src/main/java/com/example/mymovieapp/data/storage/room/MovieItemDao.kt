package com.example.mymovieapp.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mymovieapp.data.storage.model.Movie

@Dao
interface MovieItemDao {
    @Query("SELECT * FROM movie_item")
    fun getMoviesList(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movie: Movie)

    @Query("DELETE FROM movie_item WHERE id=:movieId")
    suspend fun deleteShopItem(movieId: Int)

}