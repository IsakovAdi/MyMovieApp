package com.example.mymovieapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.presentation.models.video.VideoUi
import com.example.mymovieapp.presentation.ui.diffCallbacks.PersonDetailsListDiffCallback
import com.example.mymovieapp.presentation.ui.diffCallbacks.TrailerItemDiffcallback
import com.example.mymovieapp.presentation.ui.viewHolders.TrailersTitleViewHolder

class TrailersTitleAdapter(
    private val listener: RvClickListener,
) : RecyclerView.Adapter<TrailersTitleViewHolder>() {

    var trailersList: List<VideoUi> = emptyList()
    set(value) {
        val callback = TrailerItemDiffcallback(value, trailersList)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        field = value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailersTitleViewHolder {
        val layout = when (viewType) {
            CLICKED -> R.layout.trailers_text_item_enabled
            UN_CLICKED -> R.layout.trailer_text_item_disabled
            else -> throw  Throwable("Unknown view type: ${viewType}")
        }
        return TrailersTitleViewHolder(LayoutInflater.from(parent.context)
            .inflate(layout, parent, false))
    }

    override fun onBindViewHolder(holder: TrailersTitleViewHolder, position: Int) {
        holder.bind(trailersList[position])
        holder.view.setOnClickListener {
            listener.onItemClick(trailersList[position])
        }
    }

    override fun getItemCount(): Int = trailersList.size

    fun changeSelected(video: VideoUi) {
        val list: List<VideoUi> = this.trailersList.onEach {
            it.isClicked = it.id == video.id
        }
        trailersList = list
    }


    override fun getItemViewType(position: Int): Int {
        return if (trailersList[position].isClicked!!) {
            CLICKED
        } else {
            UN_CLICKED
        }

    }

    interface RvClickListener {
        fun onItemClick(video: VideoUi)
    }

    companion object {
        const val CLICKED = 1
        const val UN_CLICKED = 0
    }

}