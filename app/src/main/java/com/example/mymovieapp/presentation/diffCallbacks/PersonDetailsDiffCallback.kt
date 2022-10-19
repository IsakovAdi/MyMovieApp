package com.example.mymovieapp.presentation.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.domain.models.person.PersonDetailsModel

class PersonDetailsDiffCallback : DiffUtil.ItemCallback<PersonDetailsModel>() {
    override fun areItemsTheSame(
        oldItem: PersonDetailsModel,
        newItem: PersonDetailsModel,
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PersonDetailsModel,
        newItem: PersonDetailsModel,
    ): Boolean {
        return oldItem == newItem
    }
}