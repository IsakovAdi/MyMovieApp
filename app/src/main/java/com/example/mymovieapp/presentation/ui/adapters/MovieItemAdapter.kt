package com.example.mymovieapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.ui.diffCallbacks.MovieDiffCallback
import com.example.mymovieapp.presentation.ui.viewHolders.ObjectViewHolder
import kotlinx.android.synthetic.main.object_item_portrait.view.*

class MovieItemAdapter(
    private val objectViewType: Int,
    private val listener: RvClickListener<MovieUi>,
) : RecyclerView.Adapter<ObjectViewHolder>() {

    var moviesList = listOf<MovieUi>()
        set(value) {
            val callback = MovieDiffCallback(moviesList, value)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val layout = when (viewType) {
            PORTRAIT_TYPE -> R.layout.object_item_portrait
            HORIZONTAL_TYPE -> R.layout.object_item_horizontal
            else -> throw RuntimeException("Unknown view type: $viewType")
        }
        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ObjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.view.setOnClickListener {
            listener.onItemClick(item = moviesList[position])
        }

        holder.view.like.setOnClickListener {
            listener.onStarClick(item = moviesList[position])
        }
        holder.bindMovie(movie = moviesList[position])


    }

    override fun getItemCount() = moviesList.size

    override fun getItemViewType(position: Int): Int {
        return if (objectViewType == PORTRAIT_TYPE) {
            PORTRAIT_TYPE
        } else HORIZONTAL_TYPE
    }

    companion object {
        const val PORTRAIT_TYPE = 1
        const val HORIZONTAL_TYPE = 0
    }

}