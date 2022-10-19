package com.example.mymovieapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.mymovieapp.databinding.FragmentStorageMoviesBinding
import com.example.mymovieapp.presentation.activities.MovieDetailsActivity
import com.example.mymovieapp.presentation.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.viewModels.StorageMoviesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StorageMoviesFragment : Fragment() {
    private val binding: FragmentStorageMoviesBinding by lazy {
        FragmentStorageMoviesBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<StorageMoviesViewModel>()

    private val moviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMovies()
        observeMovies()
        viewModel.empty.observe(viewLifecycleOwner) {
            binding.emptyText.text = it.message
        }
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it)
            binding.moviesRv.adapter = moviesAdapter
        }
        setupRvClickListeners()
    }

    private fun setupRvClickListeners() {
        moviesAdapter.onMovieItemClickListener = {
            val intent = MovieDetailsActivity.launchMovieDetailsActivity(requireContext(), it)
            startActivity(intent)
        }

        moviesAdapter.onMovieItemLongClickListener = {
            viewModel.deleteMovie(it.id)
            observeMovies()
        }
    }


}