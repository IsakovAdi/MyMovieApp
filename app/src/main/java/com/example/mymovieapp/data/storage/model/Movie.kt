package com.example.mymovieapp.data.storage.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mymovieapp.data.storage.GenreIdsConverter

@Entity(tableName = "movie_item")
class Movie(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val posterPath: String?,
    val adult: Boolean,
    val overview: String,
    val releaseDate: String?,
    val originalTitle: String?,
    val originalLanguage: String,
    val title: String?,
    val backdropPath: String?,
    val popularity: Double,
    val voteCount: Int,
    val video: Boolean,
    val rating: Double,
    @TypeConverters(GenreIdsConverter::class)
    val genre_ids: List<Int>
)