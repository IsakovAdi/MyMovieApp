package com.example.mymovieapp.data.cloud.mappers.movie

import com.example.mymovieapp.data.cloud.model.movie.MovieCloud
import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.domain.Mapper

class MapCloudMovieListToData(private val mapper: Mapper<MovieCloud, MovieData>) :
    Mapper<List<MovieCloud>, List<MovieData>> {
    override fun map(from: List<MovieCloud>) = from.run {
        map(mapper::map)
    }
}