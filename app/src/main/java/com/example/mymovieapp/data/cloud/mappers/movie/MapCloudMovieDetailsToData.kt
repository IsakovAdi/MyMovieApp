package com.example.mymovieapp.data.cloud.mappers.movie

import com.example.mymovieapp.data.cloud.model.movie.MovieDetailsCloud
import com.example.mymovieapp.data.models.movie.MovieDetailsData
import com.example.mymovieapp.data.cloud.timeFormat
import com.example.mymovieapp.domain.Mapper

class MapCloudMovieDetailsToData : Mapper<MovieDetailsCloud, MovieDetailsData> {
    override fun map(from: MovieDetailsCloud) = from.run {
        MovieDetailsData(
            backdrop_path = backdrop_path,
            budget = budget,
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            runtime = timeFormat(runtime),
            status = status,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount
        )
    }
}