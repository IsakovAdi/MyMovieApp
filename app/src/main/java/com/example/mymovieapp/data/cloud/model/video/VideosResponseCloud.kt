package com.example.mymovieapp.data.cloud.model.video

import com.google.gson.annotations.SerializedName

data class VideosResponseCloud(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val trailerList: List<VideoCloud>
)
