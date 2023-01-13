package com.example.mymovieapp.data.storage.mappers

import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.storage.model.MovieStorage
import com.example.mymovieapp.domain.Mapper

class MapStorageMovieToData : Mapper<MovieStorage, MovieData> {
    override fun map(from: MovieStorage) = from.run {
        MovieData(
            posterPath = posterPath,
            adult = adult,
            overview = overview,
            releaseDate = releaseDate,
            id = id,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            title = title,
            backdropPath = backdropPath,
            popularity = popularity,
            voteCount = voteCount,
            video = video,
            rating = rating,
            genre_ids = genre_ids.map { it }
        )
    }

}