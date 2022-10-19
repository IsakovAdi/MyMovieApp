package com.example.mymovieapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mymovieapp.R
import com.example.mymovieapp.domain.models.person.PersonModel
import com.example.mymovieapp.presentation.viewHolders.ObjectViewHolder
import com.example.mymovieapp.presentation.diffCallbacks.PersonItemDiffCallback

class PersonItemAdapter :
    ListAdapter<PersonModel, ObjectViewHolder>(PersonItemDiffCallback()) {

    var onPersonItemClickListener: ((PersonModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
        ObjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.object_item_portrait, parent, false
            )
        )

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.view.setOnClickListener {
            onPersonItemClickListener?.invoke(getItem(position))
        }
        holder.bindPerson(getItem(position))
    }
}
