package com.example.mymovieapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.data.cloud.Utils
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.ui.diffCallbacks.MovieDiffCallback
import com.squareup.picasso.Picasso
import com.vaibhavlakhera.circularprogressview.CircularProgressView

class StorageMoviesAdapter(
    private val listener: RvClickListener<MovieUi>,
) : ListAdapter<MovieUi, StorageMoviesAdapter.MovieViewHolder>(MovieDiffCallback()) {

    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        private val image = view.findViewById<ImageView>(R.id.posterImage)
        private val progressView = view.findViewById<CircularProgressView>(R.id.progressView)

        fun bind(movieUi: MovieUi) {
            Picasso.get().load(Utils.POSTER_PATH_URL + movieUi.posterPath).into(image)
            val voteAverage = (movieUi.popularity * 10.0)
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.object_item_portrait,
                parent,
                false
            )
    )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.setOnClickListener {
            listener.onItemClick(getItem(position))
        }
        holder.bind(getItem(position))
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<MovieUi>() {
    override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi) =
        oldItem.posterPath == newItem.posterPath

    override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi) = oldItem == newItem

}

