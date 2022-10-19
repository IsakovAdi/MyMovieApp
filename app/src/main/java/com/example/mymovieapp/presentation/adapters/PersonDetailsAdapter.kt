package com.example.mymovieapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mymovieapp.R
import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.presentation.diffCallbacks.PersonDetailsDiffCallback
import com.example.mymovieapp.presentation.viewHolders.ObjectViewHolder

class PersonDetailsAdapter :
    ListAdapter<PersonDetailsModel, ObjectViewHolder>(PersonDetailsDiffCallback()) {

    var onPersonItemClickListener: ((PersonDetailsModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
        ObjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.object_item_horizontal, parent, false)
        )

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.view.setOnClickListener {
            onPersonItemClickListener?.invoke(getItem(position))
        }
        holder.bindPersonDetails(getItem(position))
    }
}