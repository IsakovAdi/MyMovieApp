package com.example.mymovieapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.R
import com.example.mymovieapp.presentation.models.person.PersonUi
import com.example.mymovieapp.presentation.ui.diffCallbacks.PersonListDiffCallback
import com.example.mymovieapp.presentation.ui.viewHolders.ObjectViewHolder

class PersonItemAdapter(
    private val listener: RvClickListener<PersonUi>,
) : RecyclerView.Adapter<ObjectViewHolder>() {

    var personsList = listOf<PersonUi>()
        set(value) {
            val callback = PersonListDiffCallback(value, personsList)
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
        ObjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.object_item_portrait, parent, false
            )
        )

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        holder.bindPerson(person = personsList[position])
        holder.view.setOnClickListener {
            listener.onItemClick(item = personsList[position])
        }
    }

    override fun getItemCount() = personsList.size
}
