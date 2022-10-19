package com.example.mymovieapp.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.storage.service.LanguageRepositoryImpl
import com.example.mymovieapp.domain.MovieTypes
import com.example.mymovieapp.domain.models.ResponseState
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.models.movie.MoviesModel
import com.example.mymovieapp.domain.usecases.language.ChangeLanguageUseCase
import com.example.mymovieapp.domain.usecases.language.GetLanguageUseCase
import com.example.mymovieapp.domain.usecases.movie.network.GetNowPlayingMovies
import com.example.mymovieapp.domain.usecases.movie.network.GetPopularMovieUseCase
import com.example.mymovieapp.domain.usecases.movie.network.GetTopRatedMoviesUseCase
import com.example.mymovieapp.domain.usecases.movie.network.GetUpcomingMovies
import com.example.mymovieapp.domain.usecases.movie.storage.SaveMovieUseCase
import kotlinx.coroutines.launch

enum class MovieType {
    POPULAR,
    NOW_PLAYING,
    TOP_RATED,
    UPCOMING
}

class MovieFragmentViewModel(
    private val getPopularMovieUseCase: GetPopularMovieUseCase,
    private val getNowPlayingMovies: GetNowPlayingMovies,
    private val getUpcomingMovies: GetUpcomingMovies,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val changeLanguageUseCase: ChangeLanguageUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
) : ViewModel() {

    private val _error: MutableLiveData<Throwable> = MutableLiveData()
    val error: LiveData<Throwable> get() = _error

    private val _movies: MutableLiveData<MoviesModel> = MutableLiveData()
    val movies: LiveData<MoviesModel> get() = _movies

    private var _moviesResponse: MutableLiveData<ResponseState> = MutableLiveData()
    val moviesResponse: LiveData<ResponseState> get() = _moviesResponse

    private var _latestPosition: MovieType = MovieType.UPCOMING
    val latestPosition: MovieType get() = _latestPosition

    private var _movieTypes: ArrayList<String> = ArrayList()
    val movieTypes: ArrayList<String> get() = _movieTypes

    private var _language: String = getLanguageUseCase.execute()

    private var responsePage = 1


    fun isUs(): Boolean {
        return _language == LanguageRepositoryImpl.US
    }

    init {
        _movieTypes = if (_language == LanguageRepositoryImpl.RU) MovieTypes.getMovieTypesRu()
        else MovieTypes.getMovieTypesEn()
    }

    fun nextPage() {
        if (_movies.value!!.page != _movies.value!!.totalPage) {
            responsePage++
        }
        getMovies(_latestPosition)
    }

    fun previousPage() {
        if (responsePage != 1) {
            responsePage--
        }
        getMovies(_latestPosition)
    }

    private fun returnPage(): Int {
        return if (_movies.value!!.page == _movies.value!!.totalPage) {
            _movies.value!!.page
        } else {
            _movies.value!!.page + 1
        }
    }

    fun getMovies(type: MovieType) {
        if (type != _latestPosition) responsePage = 1
        when (type) {
            MovieType.POPULAR -> {
                getPopularMovies(_language, responsePage)
                _latestPosition = type
            }

            MovieType.NOW_PLAYING -> {
                getNowPlayingMovies(_language, responsePage)
                _latestPosition = type
            }

            MovieType.TOP_RATED -> {
                getTopRatedMovies(_language, responsePage)
                _latestPosition = type
            }

            else -> {
                getUpcomingMovies(_language, responsePage)
                _latestPosition = type
            }
        }

    }

    private fun initMoviesResponseState() {
        _moviesResponse.value = ResponseState(
            totalPage = _movies.value!!.totalPage,
            page = _movies.value!!.page,
            previousPage = _movies.value!!.page - 1,
            nextPage = returnPage(),
            isHasPreviousPage = (_movies.value!!.page - 1) != 0,
            isHasNextPage = (_movies.value!!.page) != _movies.value!!.totalPage
        )
        responsePage = _moviesResponse.value!!.page
    }

    private fun getPopularMovies(language: String, page: Int) = viewModelScope.launch {
        kotlin.runCatching {
            getPopularMovieUseCase.execute(language, page)
        }
            .onSuccess {
                _movies.value = it
                _moviesResponse.value?.totalPage = it.totalPage
                initMoviesResponseState()
            }
            .onFailure {
                _error.value = it
            }
    }

    private fun getNowPlayingMovies(language: String, page: Int) = viewModelScope.launch {
        kotlin.runCatching {
            getNowPlayingMovies.execute(language, page)
        }
            .onSuccess {
                _movies.value = it
                _moviesResponse.value?.totalPage = it.totalPage
                initMoviesResponseState()
            }
            .onFailure {
                _error.value = it
            }
    }

    private fun getUpcomingMovies(language: String, page: Int) = viewModelScope.launch {
        kotlin.runCatching {
            getUpcomingMovies.execute(language, page)
        }
            .onSuccess {
                _movies.value = it
                _moviesResponse.value?.totalPage = it.totalPage
                initMoviesResponseState()
            }
            .onFailure {
                _error.value = it
            }
    }

    private fun getTopRatedMovies(language: String, page: Int) = viewModelScope.launch {
        kotlin.runCatching {
            getTopRatedMoviesUseCase.execute(language, page)
        }
            .onSuccess {
                _movies.value = it
                _moviesResponse.value?.totalPage = it.totalPage
                initMoviesResponseState()
            }
            .onFailure {
                _error.value = it
            }
    }

    fun changeLanguage() {
        viewModelScope.launch {
            _language = changeLanguageUseCase.execute()
        }
        _movieTypes = if (_language == LanguageRepositoryImpl.US) {
            MovieTypes.getMovieTypesEn()
        } else {
            MovieTypes.getMovieTypesRu()
        }
        getMovies(MovieType.POPULAR)
    }

    fun saveMovie(movie: MovieModel) = viewModelScope.launch {
        saveMovieUseCase.execute(movie)
    }

}