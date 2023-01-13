package com.example.mymovieapp.presentation.ui.viewHolders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.data.cloud.Utils
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi
import com.example.mymovieapp.presentation.models.person.PersonUi
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class ObjectViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.posterImage)
    private val progressView = view.findViewById<CircularProgressView>(R.id.progressView)

    fun bindMovie(movie: MovieUi) {
        bind(movie.posterPath, movie.rating)
    }

    fun bindPerson(person: PersonUi) {
        bind(person.profile_path, person.popularity)
    }

    fun bindPersonDetails(person: PersonDetailsUi) {
        bind(person.profile_path, person.popularity)
    }

    private fun bind(posterPath: String?, popularity: Double) {
        Picasso.get().load(Utils.POSTER_PATH_URL + posterPath).into(image)
        val voteAverage = (popularity * 10.0)
        if (voteAverage.toInt() >= 70) {
            progressView.setProgressColorRes(R.color.green)
        } else if (voteAverage.toInt() in 51..69) {
            progressView.setProgressColorRes(R.color.yellow)
        } else {
            progressView.setProgressColorRes(R.color.red)
        }
        progressView.setProgress(voteAverage.toInt(), true)
    }
}