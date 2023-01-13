package com.example.mymovieapp.presentation.ui.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.domain.models.person.PersonDomain
import com.example.mymovieapp.presentation.models.person.PersonUi

class PersonListDiffCallback(
    private val oldList: List<PersonUi>,
    private val newList: List<PersonUi>,
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]
}