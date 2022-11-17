package com.example.mymovieapp.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.storage.service.LanguageRepositoryImpl
import com.example.mymovieapp.domain.models.movie.MovieDetailsState
import com.example.mymovieapp.domain.models.movie.MovieDetailsModel
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.models.movie.MoviesModel
import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.domain.models.video.VideoModel
import com.example.mymovieapp.domain.usecases.language.GetLanguageUseCase
import com.example.mymovieapp.domain.usecases.movie.network.GetMovieDetailsUseCase
import com.example.mymovieapp.domain.usecases.movie.network.GetRecommendMoviesUseCase
import com.example.mymovieapp.domain.usecases.movie.network.GetSimilarMoviesUseCase
import com.example.mymovieapp.domain.usecases.movie.storage.SaveMovieUseCase
import com.example.mymovieapp.domain.usecases.person.GetPersonDetailsUseCase
import com.example.mymovieapp.domain.usecases.video.GetVideosUseCase
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    private val getPersonDetailsUseCase: GetPersonDetailsUseCase,
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase,
    private val getRecommendMoviesUseCase: GetRecommendMoviesUseCase,
    private val getLanguageUseCase: GetLanguageUseCase,
    private val saveMovieUseCase: SaveMovieUseCase,
    private val getVideosUseCase: GetVideosUseCase,
) : ViewModel() {

    private val _movie: MutableLiveData<MovieDetailsModel> = MutableLiveData()
    val movie: LiveData<MovieDetailsModel> get() = _movie

    private val _persons: MutableLiveData<List<PersonDetailsModel>> = MutableLiveData()
    val persons: LiveData<List<PersonDetailsModel>> get() = _persons

    private val _similarMovies: MutableLiveData<MoviesModel> = MutableLiveData()
    val similarMovies: LiveData<MoviesModel> get() = _similarMovies

    private val _recommendMovies: MutableLiveData<MoviesModel> = MutableLiveData()
    val recommendMovies: LiveData<MoviesModel> get() = _recommendMovies

    private val _movieError: MutableLiveData<Throwable> = MutableLiveData()
    val movieError: LiveData<Throwable> get() = _movieError

    private val _similarMoviesError: MutableLiveData<Throwable> = MutableLiveData()
    val similarMoviesError: LiveData<Throwable> get() = _similarMoviesError

    private val _recommendMoviesError: MutableLiveData<Throwable> = MutableLiveData()
    val recommendMoviesError: LiveData<Throwable> get() = _recommendMoviesError

    private val _personsError: MutableLiveData<Throwable> = MutableLiveData()
    val personsError: LiveData<Throwable> get() = _personsError

    private val _videos: MutableLiveData<List<String>> = MutableLiveData()
    val videos: LiveData<List<String>> get() = _videos

    private val _trailers:MutableLiveData<List<VideoModel>> = MutableLiveData()

    private var _noVideo: String = ""
    val noVideo get() = _noVideo

    private var _language = getLanguageUseCase.execute()

    private lateinit var _detailsState: MovieDetailsState
    val detailsState: MovieDetailsState get() = _detailsState

    init {
        getDetailsState()
    }

    fun getSimilarMovies(movieId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            getSimilarMoviesUseCase.execute(movieId, _language)
        }
            .onSuccess {
                _similarMovies.value = it
            }
            .onFailure {
                _similarMoviesError.value = it
            }
    }

    fun getRecommendMovies(movieId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            getRecommendMoviesUseCase.execute(movieId, _language)
        }
            .onSuccess {
                _recommendMovies.value = it
            }
            .onFailure {
                _recommendMoviesError.value = it
            }
    }

    fun getActors(actorsId: List<Int>?) = viewModelScope.launch {
        val persons = arrayListOf<PersonDetailsModel>()
        if (actorsId != null && actorsId.isNotEmpty()) {
            for (i in actorsId.indices) {
                kotlin.runCatching {
                    getPersonDetailsUseCase.execute(actorsId[i], _language)
                }
                    .onSuccess {
                        persons.add(it)
                    }
                    .onFailure {
                        _personsError.value = it
                    }
            }
        }
        if (persons.isNotEmpty()) _persons.value = persons
        else _personsError.value = Throwable(message = "Something went wrong")
    }

    fun getMovie(movieId: Int) = viewModelScope.launch {
        getTrailers(movieId)
        kotlin.runCatching {
            getMovieDetailsUseCase.execute(movieId, _language)
        }
            .onSuccess {
                _movie.value = it
            }
            .onFailure {
                _movieError.value = it
            }
    }

    private fun getDetailsState() {
        _detailsState = if (_language == LanguageRepositoryImpl.RU) {
            MovieDetailsState.getRuDetails()
        } else {
            MovieDetailsState.getUsDetails()
        }
    }

    fun saveMovie(movie: MovieModel) = viewModelScope.launch {
        saveMovieUseCase.execute(movie)
    }

    private fun getTrailers(movieId: Int) = viewModelScope.launch {
        kotlin.runCatching {
            getVideosUseCase.execute(movieId, _language)
        }.onSuccess {
            _trailers.value = it.trailerList
        }.onFailure {
            _noVideo = "No trailers"
        }
        Log.d("workFuns", "getTrailers")
//        initVideosList(_trailers!!.value)
    }

//    private fun initVideosList(v: List<VideoModel>) {
//        viewModelScope.launch {
//                for (i in v.indices) {
//                    videos.add(v[i].key)
//                }
//
//            if (videos.isEmpty()) {
//                _videos.value = videos
//            } else {
//                _noVideo = "No trailers"
//            }
//        }
//        Log.d("workFuns", "initVideosList")
//    }

}