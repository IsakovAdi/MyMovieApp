package com.example.mymovieapp.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.mymovieapp.R
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi
import com.example.mymovieapp.presentation.ui.diffCallbacks.PersonDetailsListDiffCallback
import com.example.mymovieapp.presentation.ui.viewHolders.ObjectViewHolder

//class PersonDetailsAdapter :
////    ListAdapter<PersonDetailsUi, ObjectViewHolder>(PersonDetailsListDiffCallback()) {
////
////    var onPersonItemClickListener: ((PersonDetailsUi) -> Unit)? = null
////
////    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectViewHolder =
////        ObjectViewHolder(
////            LayoutInflater.from(parent.context)
////                .inflate(R.layout.object_item_horizontal, parent, false)
////        )
////
////    override fun onBindViewHolder(holder: ObjectViewHolder, position: Int) {
////        holder.view.setOnClickListener {
////            onPersonItemClickListener?.invoke(getItem(position))
////        }
////        holder.bindPersonDetails(getItem(position))
////    }
//}