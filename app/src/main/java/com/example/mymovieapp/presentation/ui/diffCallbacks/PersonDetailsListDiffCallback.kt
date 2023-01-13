package com.example.mymovieapp.presentation.ui.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi

class PersonDetailsListDiffCallback(
    private val oldList: List<PersonDetailsUi>,
    private val newList: List<PersonDetailsUi>,
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

}