package com.example.mymovieapp.domain.models.video

data class VideosResponseDomain(
    val id: Int,
    val trailerList: List<VideoDomain>
)
