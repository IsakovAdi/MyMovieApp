package com.example.mymovieapp.presentation.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.domain.models.movie.MovieModel

class MovieItemDiffCallback : DiffUtil.ItemCallback<MovieModel>() {
    override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
        return oldItem == newItem
    }
}