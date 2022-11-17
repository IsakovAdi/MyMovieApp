package com.example.mymovieapp.presentation.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.mymovieapp.R
import com.example.mymovieapp.data.network.Utils.POSTER_PATH_URL
import com.example.mymovieapp.databinding.ActivityMovieDetailsBinding
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.presentation.adapters.MovieItemAdapter
import com.example.mymovieapp.presentation.adapters.PersonDetailsAdapter
import com.example.mymovieapp.presentation.viewModels.MovieDetailsViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

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

    private val actorsAdapter: PersonDetailsAdapter by lazy {
        PersonDetailsAdapter()
    }
    private lateinit var spinnerAdapter: ArrayAdapter<String>

    private lateinit var genresAdapter: ArrayAdapter<String>

    private var trailers = emptyList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.videosSpinner?.onItemSelectedListener = this
        setContentView(binding.root)
        parseIntent()
        parsePageLanguage()
        makeResponse()
        observeMovie()
        setupRecommendMoviesRv()
        setupSimilarMoviesRv()
        setupActors()
        viewModel.videos.observe(this) {
            trailers = it
            initSpinner()
            initYoutubePlayer(trailers[0])
            Log.d("workFuns", "trailers size ${trailers.size}")
        }

    }

    private fun parseIntent() {
        movie = intent.getParcelableExtra(MOVIE)
    }

    private fun parsePageLanguage() {
        val details = viewModel.detailsState
        binding.apply {
            movieBudget.text = details.budget
            movieGenres.text = details.genres
            movieStatus.text = details.status
            movieTitle.text = details.movieTitle
            moviePopularity.text = details.moviePopularity
            movieVoteCount.text = details.movieVoteCount
            movieVoteAverage.text = details.rating
            movieOriginalLanguage.text = details.originalLanguage
            movieOriginalTitle.text = details.originalTitle
            movieReleaseDate.text = details.release
            movieOverview.text = details.description
            actorsText.text = details.actors
            videoText.text = details.videos
            similarMoviesText.text = details.similarMoviesText
            recommendMoviesText.text = details.recommendMoviesText
            movieRuntime.text = details.movieRuntime
        }
    }

    private fun makeResponse() {
        viewModel.getMovie(movie!!.id)
        viewModel.getRecommendMovies(movie!!.id)
        viewModel.getSimilarMovies(movie!!.id)
        viewModel.getActors(movie!!.genre_ids)
    }

    private fun observeMovie() {
        viewModel.movie.observe(this) { movie ->
            parseToolbar(movie.title)
            Picasso.get().load(POSTER_PATH_URL + movie.backdrop_path).into(binding.moviePosterImage)
            binding.apply {
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
            }
            initGenresList(movie.genres)
        }
    }

    private fun parseToolbar(title: String) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title
    }

    private fun initGenresList(genres: List<String>) {
        genresAdapter = ArrayAdapter(
            this@MovieDetailsActivity, R.layout.spinner_custom_item, genres
        )
        binding.genresListView.adapter = genresAdapter
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
        recommendMoviesAdapter.onMovieItemLongClickListener = {
            viewModel.saveMovie(it)
        }
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
        similarMoviesAdapter.onMovieItemLongClickListener = {
            viewModel.saveMovie(it)
        }
    }

    private fun setupActors() {
        viewModel.persons.observe(this@MovieDetailsActivity) {
            actorsAdapter.submitList(it)
        }
        binding.actorsRv.adapter = actorsAdapter
        setupActorsClickListener()
    }

    private fun setupActorsClickListener() {
        actorsAdapter.onPersonItemClickListener = {
            val intent =
                PersonDetailsActivity.launchPersonDetailsActivity(this@MovieDetailsActivity, it)
            startActivity(intent)
        }
    }

    private fun initYoutubePlayer(videoKey: String) {
        binding.youtubePlayer.addYouTubePlayerListener(object : YouTubePlayerListener {
            override fun onApiChange(youTubePlayer: YouTubePlayer) {
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
            }

            override fun onError(youTubePlayer: YouTubePlayer, error: PlayerConstants.PlayerError) {
            }

            override fun onPlaybackQualityChange(
                youTubePlayer: YouTubePlayer,
                playbackQuality: PlayerConstants.PlaybackQuality,
            ) {
            }

            override fun onPlaybackRateChange(
                youTubePlayer: YouTubePlayer,
                playbackRate: PlayerConstants.PlaybackRate,
            ) {
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(videoKey, 0f)
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState,
            ) {
            }

            override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {
            }

            override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {

            }

            override fun onVideoLoadedFraction(
                youTubePlayer: YouTubePlayer,
                loadedFraction: Float,
            ) {
            }

        })
        Log.d("workFuns", "initYoutubePlayer")
    }

    private fun initSpinner() {
        spinnerAdapter = ArrayAdapter(
            this, R.layout.spinner_custom_item, trailers
        )
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_custom_item)
        binding.videosSpinner?.adapter = spinnerAdapter
        Log.d("workFuns", "initSpinner")
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        initYoutubePlayer(trailers[position])
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

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