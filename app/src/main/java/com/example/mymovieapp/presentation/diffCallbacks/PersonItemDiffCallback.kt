package com.example.mymovieapp.presentation.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.domain.models.person.PersonModel

class PersonItemDiffCallback : DiffUtil.ItemCallback<PersonModel>() {
    override fun areItemsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PersonModel, newItem: PersonModel): Boolean {
        return oldItem == newItem
    }
}