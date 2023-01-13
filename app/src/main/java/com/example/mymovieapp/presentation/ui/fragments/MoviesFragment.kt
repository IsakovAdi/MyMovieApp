package com.example.mymovieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.FragmentMoviesBinding
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.ui.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.ui.adapters.RvClickListener
import com.example.mymovieapp.presentation.ui.viewModels.MovieTypes
import com.example.mymovieapp.presentation.ui.viewModels.MoviesFragmentViewModel
import com.example.mymovieapp.presentation.ui.viewModels.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MoviesFragment :
    Fragment(),
    RvClickListener<MovieUi> {

    private val viewModel by viewModels<MoviesFragmentViewModel>()
    private val binding: FragmentMoviesBinding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
    }
    private val moviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this@MoviesFragment)
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
        setAdapterToRv()
        setupClickers()
        observe()
    }

    private fun setupClickers() {
        binding.apply {
            categoriesTab.apply {
                popularMoviesItem.setOnClickListener {
                    viewModel.changeMovieCategory(MovieTypes.POPULAR)
                    selectedMovieCategoryText.text = getString(R.string.popular_category)
                }
                topRatedMoviesItem.setOnClickListener {
                    viewModel.changeMovieCategory(MovieTypes.TOP_RATED)
                    selectedMovieCategoryText.text = getString(R.string.top_rated_category)
                }
                nowPlayingMoviesItem.setOnClickListener {
                    viewModel.changeMovieCategory(MovieTypes.NOW_PLAYING)
                    selectedMovieCategoryText.text = getString(R.string.now_playing_category)
                }
                upcomingMoviesItem.setOnClickListener {
                    viewModel.changeMovieCategory(MovieTypes.UPCOMING)
                    selectedMovieCategoryText.text = getString(R.string.upcoming_category)
                }
            }

            nextBtn.setOnClickListener {
                viewModel.nextPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtn.setOnClickListener {
                viewModel.previousPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    private fun setAdapterToRv() {
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun observe() {
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }

        lifecycleScope.launchWhenResumed {
            viewModel.movies.collectLatest {
                moviesAdapter.moviesList = it.movies
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.movieResponseState.collectLatest { state ->
                binding.apply {
                    prevPageText.text = state.previousPage.toString()
                    currentPageText.text = state.page.toString()
                    nextPageText.text = state.nextPage.toString()
                    prevBtn.apply {
                        isClickable = state.isHasPreviousPage
                        isFocusable = state.isHasPreviousPage
                    }
                    nextBtn.apply {
                        isClickable = state.isHasNextPage
                        isFocusable = state.isHasNextPage
                    }
                }
            }
        }
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(item)
    }
//    {
//        findNavController().navigate(
//            RootFragmentDirections.actionRootFragmentToMovieDetailsFragment2(movie))
//    }

    override fun onLongClick(item: MovieUi) = Unit
//    {
//        viewModel.saveMovie(movie)
//        makeToast("Фильм ${movie.title} сохранен")
//    }
}