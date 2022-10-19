package com.example.mymovieapp.presentation.viewHolders

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.data.network.Utils
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.domain.models.person.PersonModel
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class ObjectViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.posterImage)
    private val progressView = view.findViewById<CircularProgressView>(R.id.progressView)

    fun bindMovie(movie: MovieModel) {
        bind(movie.posterPath, movie.rating)
    }

    fun bindPerson(person: PersonModel) {
        bind(person.profile_path, person.popularity)
    }

    fun bindPersonDetails(person:PersonDetailsModel){
        bind(person.profile_path, person.popularity)
    }

    private fun bind(posterPath:String?, popularity:Double){
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