package com.example.mymovieapp.data.entities

import com.google.gson.annotations.SerializedName

data class Movies(
    val page:Int,
    @SerializedName("results")
    var movies:List<Movie>,
    @SerializedName("total_page")
    val totalPage:Int
)
