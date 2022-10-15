package com.example.mymovieapp.data.network.model.response.video

import com.google.gson.annotations.SerializedName

data class Videos(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val trailerList: List<Video>
)
