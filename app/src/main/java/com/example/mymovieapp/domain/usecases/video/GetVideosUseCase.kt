package com.example.mymovieapp.domain.usecases.video

import com.example.mymovieapp.domain.repositories.VideoRepository


class GetVideosUseCase(private val repository: VideoRepository) {
    suspend fun execute(movieId: Int) = repository.getVideos(movieId)
}