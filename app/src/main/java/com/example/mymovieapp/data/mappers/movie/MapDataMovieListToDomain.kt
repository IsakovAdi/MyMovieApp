package com.example.mymovieapp.data.mappers.movie

import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain

class MapDataMovieListToDomain(private val mapper: Mapper<MovieData, MovieDomain>) :
    Mapper<List<MovieData>, List<MovieDomain>> {
    override fun map(from: List<MovieData>) = from.run {
        map(mapper::map)
    }
}