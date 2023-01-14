package com.example.mymovieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymovieapp.data.cloud.Utils
import com.example.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.presentation.models.movie.MovieDetailsUi
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi
import com.example.mymovieapp.presentation.ui.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.ui.adapters.PersonDetailsAdapter
import com.example.mymovieapp.presentation.ui.adapters.PersonItemAdapter
import com.example.mymovieapp.presentation.ui.adapters.RvClickListener
import com.example.mymovieapp.presentation.ui.viewModels.MovieDetailsViewModel
import com.example.mymovieapp.presentation.ui.viewModels.MovieDetailsViewModelFactory
import com.example.mymovieapp.presentation.ui.viewModels.makeToast
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(),
    RvClickListener<MovieUi>, PersonDetailsAdapter.RvClickListener {
    private val binding: FragmentMovieDetailsBinding by lazy {
        FragmentMovieDetailsBinding.inflate(layoutInflater)
    }
    private val movieId: Int by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.id
    }
    private val actorsIds: List<Int> by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.genre_ids
    }

    @Inject
    lateinit var viewModelFactory: MovieDetailsViewModelFactory.Factory
    private val viewModel by viewModels<MovieDetailsViewModel> {
        viewModelFactory.create(movieId = movieId, actorsIds = actorsIds)
    }

    private val similarMoviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.HORIZONTAL_TYPE, this@MovieDetailsFragment)
    }

    private val recommendMoviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.HORIZONTAL_TYPE, this@MovieDetailsFragment)
    }

    private val personAdapter: PersonDetailsAdapter by lazy {
        PersonDetailsAdapter(this@MovieDetailsFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdaptersToRv()
        observe()
    }

    private fun setAdaptersToRv() {
        binding.apply {
            recommendMoviesRv.adapter = recommendMoviesAdapter
            similarMoviesRv.adapter = similarMoviesAdapter
            actorsRv.adapter = personAdapter
        }
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.movieFlow.collectLatest {
                when (it) {
                    is DataRequestState.Success -> {
                        setMovieUi(it.data)
                    }
                    is DataRequestState.Error -> {
                        makeToast(it.error.message.toString(), requireContext())
                    }
                }
            }
        }

        viewModel.persons.onEach {
            personAdapter.personsList = it
        }
        lifecycleScope.launchWhenStarted {
            viewModel.similarMoviesFlow.collectLatest {
                similarMoviesAdapter.moviesList = it.movies
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.recommendMoviesFlow.collectLatest {
                recommendMoviesAdapter.moviesList = it.movies
            }
        }
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }

    }

    private fun setMovieUi(movie: MovieDetailsUi) {
        with(binding) {
            title.text = movie.title
            popularity.text = movie.popularity.toString()
            voteCount.text = movie.voteCount.toString()
            budget.text = movie.budget.toString()
            voteAverage.rating = movie.voteAverage.toFloat()
            originalLanguage.text = movie.originalLanguage
            originalTitle.text = movie.originalTitle
            releaseDate.text = movie.releaseDate
            runtime.text = movie.runtime
            status.text = movie.status
            overview.text = movie.overview
            Picasso.get().load(
                Utils.POSTER_PATH_URL + movie.backdrop_path
            ).into(moviePosterImage)
            toolbar.title = movie.title
        }
    }

    override fun onItemClick(item: MovieUi) {
        TODO("Not yet implemented")
    }

    override fun onLongClick(item: MovieUi) {
        TODO("Not yet implemented")
    }

    override fun onPersonItemClick(person: PersonDetailsUi) = Unit

}