package com.example.mymovieapp.presentation.ui.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.presentation.models.video.VideoUi

class TrailersTitleViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val trailersTitle: TextView = view.findViewById(R.id.trailer_title)

    fun bind(trailer: VideoUi) {
        trailer.name.let {
            trailersTitle.text = it
        }
    }
}