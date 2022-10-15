package com.example.mymovieapp.presentation.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mymovieapp.R
import com.example.mymovieapp.data.network.Utils.POSTER_PATH_URL
import com.example.mymovieapp.databinding.ActivityMainBinding
import com.example.mymovieapp.databinding.ActivityMovieDetailsBinding
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.presentation.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.adapters.PersonItemAdapter
import com.example.mymovieapp.presentation.viewModels.MovieDetailsViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsActivity : AppCompatActivity() {

    private val binding: ActivityMovieDetailsBinding by lazy {
        ActivityMovieDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<MovieDetailsViewModel>()
    private var movie: MovieModel? = null
    private val similarMoviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.HORIZONTAL_TYPE)
    }

    private val recommendMoviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.HORIZONTAL_TYPE)
    }

    private val actorsAdapter: PersonItemAdapter by lazy {
        PersonItemAdapter(PersonItemAdapter.HORIZONTAL_TYPE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        parseIntent()
        parsePageLanguage()
        makeResponse()
        observeMovie()
        setupRecommendMoviesRv()
        setupSimilarMoviesRv()
    }


    private fun parseToolbar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    private fun setupActors() {
        binding.actorsRv.setHasFixedSize(true)

    }

    private fun makeResponse() {
        viewModel.getMovie(movie!!.id)
        viewModel.getRecommendMovies(movie!!.id)
        viewModel.getSimilarMovies(movie!!.id)
        viewModel.getActors(movie!!.genre_ids)
    }

    private fun setupSimilarMoviesRv() {
        viewModel.similarMovies.observe(this@MovieDetailsActivity) {
            similarMoviesAdapter.submitList(it.movies)
        }
        binding.similarMoviesRv.adapter = similarMoviesAdapter
        setupSimilarMoviesClickListener()
    }

    private fun setupSimilarMoviesClickListener() {
        similarMoviesAdapter.onMovieItemClickListener = {
            val intent = launchMovieDetailsActivity(this, it)
            startActivity(intent)
        }
    }

    private fun setupRecommendMoviesRv() {
        viewModel.recommendMovies.observe(this@MovieDetailsActivity) {
            recommendMoviesAdapter.submitList(it.movies)
        }
        binding.recommendMoviesRv.adapter = recommendMoviesAdapter
        setupRecommendMoviesClickListener()
    }

    private fun setupRecommendMoviesClickListener() {
        recommendMoviesAdapter.onMovieItemClickListener = {
            val intent = launchMovieDetailsActivity(this, it)
            startActivity(intent)
        }
    }

    private fun observeMovie() {
        viewModel.movie.observe(this) { movie ->
            parseToolbar(movie.title!!)
            Picasso.get().load(POSTER_PATH_URL + movie.backdrop_path).into(binding.moviePosterImage)
            binding.apply {
                title.text = movie.title
                popularity.text = movie.popularity.toString()
                voteCount.text = movie.voteCount.toString()
                voteAverage.rating = movie.voteAverage.toFloat()
                originalLanguage.text = movie.originalLanguage
                originalTitle.text = movie.originalTitle
                relaseDate.text = movie.releaseDate
                overivew.text = movie.overview
            }

        }
    }

    private fun parsePageLanguage() {
        val details = viewModel.detailsState
        binding.apply {
            movieTitle.text = details.movieTitle
            moviePopularity.text = details.moviePopularity
            movieVoteCount.text = details.movieVoteCount
            movieVoteAverage.text = details.rating
            movieOriginalLanguage.text = details.originalLanguage
            movieOriginalTitle.text = details.originalTitle
            movieRelaseDate.text = details.release
            movieOverview.text = details.description
            actorsText.text = details.actors
            videoText.text = details.videos
            similarMoviesText.text = details.similarMoviesText
            recommendMoviesText.text = details.recommendMoviesText
        }
    }

    private fun parseIntent() {
        movie = intent.getParcelableExtra(MOVIE)

    }

    companion object {
        fun launchMovieDetailsActivity(context: Context, movie: MovieModel): Intent {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE, movie)
            return intent
        }

        private const val MOVIE = "movie"
    }

}