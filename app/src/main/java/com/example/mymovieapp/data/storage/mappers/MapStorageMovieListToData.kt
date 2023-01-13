package com.example.mymovieapp.data.storage.mappers

import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.storage.model.MovieStorage
import com.example.mymovieapp.domain.Mapper

class MapStorageMovieListToData(
    private val mapper: Mapper<MovieStorage, MovieData>,
) : Mapper<List<MovieStorage>, List<MovieData>> {
    override fun map(from: List<MovieStorage>) = from.run {
        map(mapper::map)
    }
}