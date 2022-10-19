package com.example.mymovieapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import com.example.mymovieapp.databinding.FragmentSearchMoviesBinding
import com.example.mymovieapp.presentation.activities.MovieDetailsActivity
import com.example.mymovieapp.presentation.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.viewModels.SearchMoviesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMoviesFragment : Fragment() {
    private val binding: FragmentSearchMoviesBinding by lazy {
        FragmentSearchMoviesBinding.inflate(layoutInflater)
    }

    private val moviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE)
    }

    private val viewModel by viewModel<SearchMoviesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        search()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movies.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it.movies)
            binding.searchProgressBar.visibility = INVISIBLE
            binding.searchRv.adapter = moviesAdapter
        }
        setupRvClickListener()
    }

    private fun setupRvClickListener() {
        moviesAdapter.onMovieItemClickListener = {
            val intent = MovieDetailsActivity.launchMovieDetailsActivity(requireContext(), it)
            startActivity(intent)
        }

        moviesAdapter.onMovieItemLongClickListener = {
            viewModel.saveMovie(it)
            Toast.makeText(requireContext(), "${it.title} saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun search() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                binding.searchProgressBar.visibility = VISIBLE
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                binding.searchProgressBar.visibility = VISIBLE
                viewModel.searchMovie(query)
                return false
            }
        })

        binding.searchView.setOnCloseListener {
            false
        }
    }
}