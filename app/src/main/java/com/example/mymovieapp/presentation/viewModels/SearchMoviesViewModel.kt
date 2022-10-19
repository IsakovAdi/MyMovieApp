package com.example.mymovieapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.models.movie.MoviesModel
import com.example.mymovieapp.domain.usecases.language.GetLanguageUseCase
import com.example.mymovieapp.domain.usecases.movie.network.SearchMovieUseCase
import com.example.mymovieapp.domain.usecases.movie.storage.SaveMovieUseCase
import kotlinx.coroutines.launch

class SearchMoviesViewModel(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val saveMovieUseCase: SaveMovieUseCase
) : ViewModel() {
    private val _movies: MutableLiveData<MoviesModel> = MutableLiveData()
    val movies: LiveData<MoviesModel> get() = _movies

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    private val _language: String = getLanguageUseCase.execute()

    fun searchMovie(query: String) = viewModelScope.launch {
        kotlin.runCatching {
            searchMovieUseCase.execute(_language, query)
        }
            .onSuccess {
                _movies.value = it
            }
            .onFailure {
                _error.value = it
            }
    }

    fun saveMovie(movie:MovieModel) = viewModelScope.launch {
        saveMovieUseCase.execute(movie)
    }
}