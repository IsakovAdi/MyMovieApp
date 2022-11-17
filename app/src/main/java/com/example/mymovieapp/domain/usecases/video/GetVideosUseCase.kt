package com.example.mymovieapp.domain.usecases.video

import com.example.mymovieapp.domain.repositories.network.VideoRepository


class GetVideosUseCase(private val repository: VideoRepository) {
    suspend fun execute(movieId: Int, language:String) = repository.getVideos(movieId, language)
}