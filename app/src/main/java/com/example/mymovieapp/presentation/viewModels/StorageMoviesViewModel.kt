package com.example.mymovieapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.usecases.movie.storage.DeleteMovieUseCase
import com.example.mymovieapp.domain.usecases.movie.storage.GetStorageMoviesUseCase
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class StorageMoviesViewModel(
    private val getStorageMoviesUseCase: GetStorageMoviesUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
) : ViewModel() {

    private val _movies = createMutableSharedFlowAsLiveData<List<MovieModel>>()
    val movies = _movies.asSharedFlow()

    init {
        getAllMovies()
    }


    fun getAllMovies() {
        viewModelScope.launch {
            val response = getStorageMoviesUseCase.execute()
            _movies.emitAll(response)
        }
    }
//    val movies: Flow<List<MovieModel>> = getStorageMoviesUseCase.execute()

    fun deleteMovie(movieId: Int) = viewModelScope.launch {
        deleteMovieUseCase.execute(movieId)
    }


}

fun <T> createMutableSharedFlowAsSingleLiveEvent(): MutableSharedFlow<T> =
    MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)

fun <T> createMutableSharedFlowAsLiveData(): MutableSharedFlow<T> =
    MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)