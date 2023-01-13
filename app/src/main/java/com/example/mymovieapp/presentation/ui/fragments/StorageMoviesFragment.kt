package com.example.mymovieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.mymovieapp.databinding.FragmentStorageMoviesBinding
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.ui.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.ui.adapters.RvClickListener
import com.example.mymovieapp.presentation.ui.adapters.StorageMoviesAdapter
import com.example.mymovieapp.presentation.ui.viewModels.StorageMoviesViewModel
import com.example.mymovieapp.presentation.ui.viewModels.makeToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StorageMoviesFragment : Fragment(), RvClickListener<MovieUi> {
    private val binding: FragmentStorageMoviesBinding by lazy {
        FragmentStorageMoviesBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<StorageMoviesViewModel>()

    private val moviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this@StorageMoviesFragment)
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
        binding.moviesRv.adapter = moviesAdapter
        observeMovies()
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.deleteMovie(item.id)
    }

    override fun onLongClick(item: MovieUi) = Unit

    private fun observeMovies() {
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }
        lifecycleScope.launchWhenStarted{
            viewModel.storageMovies
                .onEach {
                    if (it.isNotEmpty()) {
                        binding.moviesRv.visibility = View.VISIBLE
                        moviesAdapter.moviesList = it
                    }
                }.catch {
                    binding.moviesRv.visibility = View.INVISIBLE
                }
                .collect()
        }
    }
}