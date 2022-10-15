package com.example.mymovieapp.data.network.model.response.movie

import com.google.gson.annotations.SerializedName

class Movies(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<Movie>,
    @SerializedName("total_pages") val totalPage: Int
)
