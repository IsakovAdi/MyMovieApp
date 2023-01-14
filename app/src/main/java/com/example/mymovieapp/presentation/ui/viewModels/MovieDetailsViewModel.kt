package com.example.mymovieapp.presentation.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.data.cloud.base.ResourceProvider
import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.GetMovieActorsUseCase
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.helper.DispatchersProvider
import com.example.mymovieapp.domain.models.movie.MovieDetailsDomain
import com.example.mymovieapp.domain.models.movie.MoviesResponseDomain
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.domain.repositories.network.MovieRepository
import com.example.mymovieapp.domain.takeSuccess
import com.example.mymovieapp.presentation.models.movie.MovieDetailsUi
import com.example.mymovieapp.presentation.models.movie.MoviesResponseUi
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieDetailsViewModel constructor(
    private val movieId: Int,
    private val actorsIds: List<Int>,
    private val movieRepository: MovieRepository,
    private val mapMovieDetails: Mapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapMovieResponse: Mapper<MoviesResponseDomain, MoviesResponseUi>,
    private val mapPersons: Mapper<List<PersonDetailsDomain>, List<PersonDetailsUi>>,
    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: ResourceProvider,
    private val getMovieActorsUseCase: GetMovieActorsUseCase,
) : ViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val movieIdFlow = MutableStateFlow(movieId)
    private val personsIds = MutableStateFlow(actorsIds)

    val movieFlow = movieIdFlow.map(movieRepository::getMovieDetails)
        .map { it.map(mapMovieDetails) }

//    val persons = personsIds.flatMapLatest(getMovieActorsUseCase::invoke)
//        .map { it.map { it.takeSuccess() }.filterNotNull() }
//        .map(mapPersons::map)

    val persons = personsIds.flatMapLatest(getMovieActorsUseCase::invoke)
        .map { it -> it.mapNotNull { it.takeSuccess() } }
        .onEach {
            it.forEach {
                Log.d("MY_LOG", it.name)
            }
        }
        .map(mapPersons::map)

    val similarMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getSimilarMovies(it)
    }.map(mapMovieResponse::map)
        .flowOn(dispatchersProvider.default())
        .onEach {
            it.movies.forEach {
                Log.d("MY_LOG", it.title.toString())
            }
        }
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getRecommendMovies(it)
    }.map(mapMovieResponse::map)
        .flowOn(dispatchersProvider.default())
        .onEach {
            it.movies.forEach {
                Log.d("MY_LOG", it.title.toString())
            }
        }
        .catch { throwable: Throwable ->
            _error.emit(resourceProvider.handleException(throwable = throwable))
        }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


}