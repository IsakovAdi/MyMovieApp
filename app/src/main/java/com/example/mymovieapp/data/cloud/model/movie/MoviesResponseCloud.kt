package com.example.mymovieapp.data.cloud.model.movie

import com.google.gson.annotations.SerializedName

data class MoviesResponseCloud(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieCloud>,
    @SerializedName("total_pages") val totalPage: Int,
)
