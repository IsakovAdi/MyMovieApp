package com.example.mymovieapp.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mymovieapp.R
import com.example.mymovieapp.databinding.FragmentMoviesBinding
import com.example.mymovieapp.presentation.activities.MovieDetailsActivity
import com.example.mymovieapp.presentation.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.viewModels.MovieFragmentViewModel
import com.example.mymovieapp.presentation.viewModels.MovieType
import org.koin.androidx.viewmodel.ext.android.viewModel


class MoviesFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val viewModel by viewModel<MovieFragmentViewModel>()
    private val binding: FragmentMoviesBinding by lazy {
        FragmentMoviesBinding.inflate(layoutInflater)
    }
    private val moviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE)
    }

    private lateinit var moviesTypeList: ArrayList<String>

    private lateinit var spinnerAdapter: ArrayAdapter<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding.languageSwitch.isChecked = viewModel.isUs()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies(MovieType.POPULAR)
        binding.movieTypeSpinner.onItemSelectedListener = this
        setupMoviesRV()
        setupSpinner()
        setupSwitch()
        setupClickers()
        observeViewModel()
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getMovies(viewModel.latestPosition)
        }
    }

    private fun setupClickers() {
        binding.apply {
            nextBtn.setOnClickListener {
                viewModel.nextPage()
                nextBtn.visibility = INVISIBLE
                binding.progressBarNext.visibility = VISIBLE
            }
            prevBtn.setOnClickListener {
                viewModel.previousPage()
                prevBtn.visibility = INVISIBLE
                binding.progressBarPrev.visibility = VISIBLE
            }
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            error.observe(viewLifecycleOwner) {
                makeToast(it.message.toString())
                binding.swipeRefresh.isRefreshing = false
            }
            moviesResponse.observe(viewLifecycleOwner) {
                binding.apply {
                    prevPageText.text = it.previousPage.toString()
                    currentPageText.text = it.page.toString()
                    nextPageText.text = it.nextPage.toString()
                }
            }
        }
    }

    private fun setupSwitch() {
        binding.languageSwitch.setOnCheckedChangeListener { buttonView, isChacked ->
            viewModel.changeLanguage()
            binding.generalProgress.visibility = VISIBLE
            setupSpinner()
        }
    }


    private fun setupSpinner() {
        moviesTypeList = viewModel.movieTypes
        spinnerAdapter = ArrayAdapter(
            requireContext(), R.layout.spinner_custom_item, moviesTypeList
        )
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_custom_item)
        binding.movieTypeSpinner.adapter = spinnerAdapter
    }

    private fun setupMoviesRV() {
        viewModel.movies.observe(viewLifecycleOwner) {
            moviesAdapter.submitList(it.movies)
            binding.swipeRefresh.isRefreshing = false
            binding.generalProgress.visibility = INVISIBLE
            setVisibilities()
            binding.moviesRv.adapter = moviesAdapter
        }
        setupMoviesRvClickListener()
    }

    private fun setupMoviesRvClickListener() {
        moviesAdapter.onMovieItemClickListener = {
            val intent = MovieDetailsActivity.launchMovieDetailsActivity(requireContext(), it)
            startActivity(intent)
        }

        moviesAdapter.onMovieItemLongClickListener = {
            viewModel.saveMovie(it)
            makeToast("${it.title} saved")
        }
    }

    private fun setVisibilities() {
        binding.apply {
            nextBtn.visibility = VISIBLE
            progressBarNext.visibility = INVISIBLE
            prevBtn.visibility = VISIBLE
            progressBarPrev.visibility = INVISIBLE
            prevPageText.visibility = VISIBLE
            currentPageText.visibility = VISIBLE
            nextPageText.visibility = VISIBLE
        }

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when (binding.movieTypeSpinner.selectedItem.toString()) {
            moviesTypeList[0] -> viewModel.getMovies(MovieType.POPULAR)
            moviesTypeList[1] -> viewModel.getMovies(MovieType.NOW_PLAYING)
            moviesTypeList[2] -> viewModel.getMovies(MovieType.TOP_RATED)
            moviesTypeList[3] -> viewModel.getMovies(MovieType.UPCOMING)
        }
        binding.generalProgress.visibility = VISIBLE
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    private fun makeToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}