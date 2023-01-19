package com.example.mymovieapp.domain

import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import com.example.mymovieapp.domain.repositories.storage.MovieStorageRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface SaveMovieFromDetailsUseCase {
    suspend fun saveMovie(movieTitle: String, movieId: Int)
}

class SaveMovieFromDetailsUseCaseImpl @Inject constructor(
    private val repository: MovieRepository,
    private val dispatchersProvider: DispatchersProvider,
    private val storageMovieRepository: MovieStorageRepository,
) : SaveMovieFromDetailsUseCase {
    override suspend fun saveMovie(movieTitle: String, movieId: Int) {
        withContext(dispatchersProvider.io()) {
            kotlin.runCatching {
                repository.searchMovie(query = movieTitle)
            }
                .onSuccess {
                    it.takeSuccess()?.movies?.forEach { movie ->
                        if (movie.id == movieId) {
                            storageMovieRepository.saveMovieToDatabase(movie)
                        }
                    }
                }
        }
    }


}