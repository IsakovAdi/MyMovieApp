package com.example.mymovieapp.presentation.fragments.moviesF

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.domain.models.MoviesModel
import com.example.mymovieapp.domain.usecases.*
import kotlinx.coroutines.launch

enum class MovieType {
    POPULAR,
    NOW_PLAYING,
    TOP_RATED,
    UPCOMING,
    SEARCH
}

class MovieFragmentViewModel(
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val getUpcomingMovies: GetUpcomingMovies,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private val _page: MutableLiveData<Int> = MutableLiveData(1)
    val page: LiveData<Int> get() = _page

    private val _totalPage: MutableLiveData<Int> = MutableLiveData()
    val totalPage: LiveData<Int> get() = _totalPage

    private var _language: String = "RU"

    private val _movies: MutableLiveData<MoviesModel> = MutableLiveData()
    val movies: LiveData<MoviesModel> get() = _movies

    private var _latestPosition: MovieType = MovieType.POPULAR
    val latestPosition get() = _latestPosition

    private var _isHasNext: MutableLiveData<Boolean> = MutableLiveData(true)
    val isHasNext: LiveData<Boolean> get() = _isHasNext

    private var _isHasPrevious: MutableLiveData<Boolean> = MutableLiveData(false)
    val isHasPrevious: LiveData<Boolean> get() = _isHasPrevious

    fun nextPage() {
        _page.value = _page.value?.plus(1)
        getMovies(_latestPosition)
        changeIsHasNextState()
    }

    fun previousPage() {
        _page.value = _page.value?.minus(1)
        getMovies(_latestPosition)
        changeIsHasPreviousState()
    }

    fun getMovies(type: MovieType) {
        if (type!=_latestPosition) changeToStartPage()
        when (type) {
            MovieType.POPULAR -> {
                getPopularMovies(_language, _page.value.toString().toInt())
                _latestPosition = type
            }

            MovieType.NOW_PLAYING -> {
                getNowPlayingMovies(_language, _page.value.toString().toInt())
                _latestPosition = type
            }

            MovieType.TOP_RATED -> {
                getTopRatedMovies(_language, _page.value.toString().toInt())
                _latestPosition = type
            }

            else -> {
                getUpcomingMovies(_language, _page.value.toString().toInt())
                _latestPosition = type
            }
        }
    }

    private fun changeIsHasNextState() {
        _isHasNext.value = _page.value != _totalPage.value
    }

    private fun changeIsHasPreviousState() {
        _isHasPrevious.value = _page.value != 1
    }

    private fun getPopularMovies(language: String, page: Int) = viewModelScope.launch {
        val moviesResponse = getPopularMovieUseCase.execute(language, page)
        if (moviesResponse != null) {
            _movies.value = moviesResponse!!
            _totalPage.value = moviesResponse!!.totalPage
        }
    }

    private fun getNowPlayingMovies(language: String, page: Int) = viewModelScope.launch {
        val moviesResponse = getNowPlayingMovies.execute(language, page)
        if (moviesResponse != null) {
            _movies.value = moviesResponse!!
            _totalPage.value = moviesResponse.totalPage
        }
    }

    private fun getUpcomingMovies(language: String, page: Int) = viewModelScope.launch {
        val moviesResponse = getUpcomingMovies.execute(language, page)
        if (moviesResponse != null) {
            _movies.value = moviesResponse!!
            _totalPage.value = moviesResponse!!.totalPage
        }
    }

    private fun getTopRatedMovies(language: String, page: Int) = viewModelScope.launch {
        val moviesResponse = getTopRatedMoviesUseCase.execute(language, page)
        if (moviesResponse != null) {
            _movies.value = moviesResponse!!
            _totalPage.value = moviesResponse!!.totalPage
        }
    }

    fun searchMovies(language: String, query: String) = viewModelScope.launch {
        val moviesResponse = searchMovieUseCase.execute(language, query)
        if (moviesResponse != null) {
            _movies.value = moviesResponse!!
            _totalPage.value = moviesResponse!!.totalPage
        }
    }

    fun changeLanguage() {
        _language = if (_language == "RU") {
            "US"
        } else {
            "RU"
        }
        getMovies(_latestPosition)
    }

    private fun changeToStartPage() {
        _page.value = 1
    }

}