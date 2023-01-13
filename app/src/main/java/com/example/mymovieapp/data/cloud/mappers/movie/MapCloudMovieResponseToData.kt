package com.example.mymovieapp.data.cloud.mappers.movie

import com.example.mymovieapp.data.cloud.model.movie.MovieCloud
import com.example.mymovieapp.data.cloud.model.movie.MoviesResponseCloud
import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.models.movie.MoviesResponseData
import com.example.mymovieapp.domain.Mapper

class MapCloudMovieResponseToData(private val mapper: Mapper<List<MovieCloud>, List<MovieData>>) :
    Mapper<MoviesResponseCloud, MoviesResponseData> {
    override fun map(from: MoviesResponseCloud) = from.run {
        MoviesResponseData(
            page = page,
            movies = mapper.map(movies),
            totalPage = totalPage
        )
    }
}