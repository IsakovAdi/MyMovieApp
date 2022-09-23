package com.example.mymovieapp.domain.usecases

import com.example.mymovieapp.domain.repository.VideoRepository

class GetVideosUseCase(private val repository: VideoRepository) {
    suspend fun execute(movieId: Int) = repository.getVideos(movieId)
}