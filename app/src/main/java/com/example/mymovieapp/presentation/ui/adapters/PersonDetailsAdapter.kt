package com.example.mymovieapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi
import com.example.mymovieapp.presentation.ui.diffCallbacks.PersonDetailsListDiffCallback
import com.example.mymovieapp.presentation.ui.viewHolders.ObjectViewHolder

class PersonDetailsAdapter(private val listener: RvClickListener) :
    RecyclerView.Adapter<ObjectViewHolder>() {

    var personsList = emptyList<PersonDetailsUi>()
        set(value) {
            val callback = PersonDetailsListDiffCallback(value, personsList)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
        ObjectViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.object_item_horizontal, parent, false)
        )

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.view.setOnClickListener {
            listener.onPersonItemClick(personsList[position])
        }
        holder.bindPersonDetails(personsList[position])
    }

    override fun getItemCount(): Int = personsList.size

    interface RvClickListener {
        fun onPersonItemClick(person: PersonDetailsUi)
    }
}