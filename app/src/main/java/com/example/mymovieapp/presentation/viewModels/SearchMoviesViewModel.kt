package com.example.mymovieapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.domain.models.movie.MoviesModel
import com.example.mymovieapp.domain.usecases.movie.SearchMovieUseCase
import kotlinx.coroutines.launch

class SearchMoviesViewModel(
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {
    private val _movies: MutableLiveData<MoviesModel> = MutableLiveData()
    val movies: LiveData<MoviesModel> get() = _movies

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    fun searchMovie(language: String, query: String) = viewModelScope.launch {
        kotlin.runCatching {
            searchMovieUseCase.execute(language, query)
        }
            .onSuccess {
                _movies.value = it
            }
            .onFailure {
                _error.value = it
            }
    }
}