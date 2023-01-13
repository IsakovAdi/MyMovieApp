package com.example.mymovieapp.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.mymovieapp.R
import com.example.mymovieapp.presentation.ui.viewModels.MovieDetailsViewModel
import com.example.mymovieapp.presentation.ui.viewModels.MovieDetailsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

}