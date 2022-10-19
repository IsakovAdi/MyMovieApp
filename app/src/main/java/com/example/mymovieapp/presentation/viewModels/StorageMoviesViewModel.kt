package com.example.mymovieapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.storage.service.LanguageRepositoryImpl
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.usecases.language.GetLanguageUseCase
import com.example.mymovieapp.domain.usecases.movie.storage.DeleteMovieUseCase
import com.example.mymovieapp.domain.usecases.movie.storage.GetStorageMoviesUseCase

import kotlinx.coroutines.launch

class StorageMoviesViewModel(
    private val getStorageMoviesUseCase: GetStorageMoviesUseCase,
    private val deleteMovieUseCase: DeleteMovieUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
) : ViewModel() {
    private val _movies: MutableLiveData<List<MovieModel>> = MutableLiveData()
    val movies: LiveData<List<MovieModel>> get() = _movies

    private val _empty: MutableLiveData<Throwable> = MutableLiveData()
    val empty: LiveData<Throwable> get() = _empty

    fun getMovies() = viewModelScope.launch {
        kotlin.runCatching {
            getStorageMoviesUseCase.execute()
        }.onSuccess {
            if (it.isNotEmpty()) {
                _movies.value = it
                _empty.value = Throwable("")
            } else {
                _movies.value = emptyList()
                _empty.value = Throwable(getMessage())
            }
        }.onFailure {
            _empty.value = Throwable(getMessage())
        }
    }

    fun deleteMovie(movieId: Int) = viewModelScope.launch {
        deleteMovieUseCase.execute(movieId)
        getMovies()
    }

    private fun getMessage(): String {
        return if (getLanguageUseCase.execute() == LanguageRepositoryImpl.RU) EMPTY_RU
        else EMPTY_US
    }

    companion object {
        private const val EMPTY_RU = "Нет сохраненных фильмов"
        private const val EMPTY_US = "No saved movies"
    }

}