package com.example.mymovieapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mymovieapp.R
import com.example.mymovieapp.domain.models.person.PersonModel
import com.example.mymovieapp.presentation.ViewHolders.ObjectViewHolder
import com.example.mymovieapp.presentation.diffCallbacks.PersonItemDiffCallback

class PersonItemAdapter(private val objectViewType: Int) :
    ListAdapter<PersonModel, ObjectViewHolder>(PersonItemDiffCallback()) {

    var onPersonItemClickListener: ((PersonModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder {
        val layout = when (viewType) {
            PORTRAIT_TYPE -> R.layout.object_item_portrait
//            HORIZONTAL_TYPE -> R.layout.object_item_horizontal
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ObjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
        val view = getItem(position)
        holder.view.setOnClickListener {
            onPersonItemClickListener?.invoke(view)
        }
        holder.bindPerson(view)
    }

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
