package com.example.mymovieapp.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.mymovieapp.databinding.FragmentSearchMoviesBinding
import com.example.mymovieapp.domain.DataRequestState
import com.example.mymovieapp.domain.takeSuccess
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.ui.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.ui.adapters.RvClickListener
import com.example.mymovieapp.presentation.ui.viewModels.SearchMoviesViewModel
import com.example.mymovieapp.presentation.ui.viewModels.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMoviesFragment
    : Fragment(),
    RvClickListener<MovieUi> {
    private val binding: FragmentSearchMoviesBinding by lazy {
        FragmentSearchMoviesBinding.inflate(layoutInflater)
    }

    private val moviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this@SearchMoviesFragment)
    }

    private val viewModel by viewModels<SearchMoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        observe()
        search()
    }

    private fun setUi() {
        binding.moviesRv.adapter = moviesAdapter
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchMovie(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    viewModel.searchMovie(query)
                }
                return false
            }
        })
    }

    private fun observe() {
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }

        lifecycleScope.launch {
            viewModel.movies.collectLatest {
                moviesAdapter.moviesList = it.movies
            }
        }
    }

    override fun onItemClick(item: MovieUi) {
        findNavController().navigate(
            SearchMoviesFragmentDirections
                .actionNavSearchToMovieDetailsFragment(item))
    }

    override fun onStarClick(item: MovieUi) {
        viewModel.saveMovie(movie = item)
        makeToast("Фильм ${item.title} сохранен", requireContext())
    }

}