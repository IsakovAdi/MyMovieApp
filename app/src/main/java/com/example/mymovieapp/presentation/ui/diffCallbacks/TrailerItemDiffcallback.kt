package com.example.mymovieapp.presentation.ui.diffCallbacks

import androidx.recyclerview.widget.DiffUtil
import com.example.mymovieapp.presentation.models.video.VideoUi

class TrailerItemDiffcallback(
    private val oldList: List<VideoUi>,
    private val newList: List<VideoUi>,
) : DiffUtil.Callback() {
    //    override fun areItemsTheSame(oldItem: VideoUi, newItem: VideoUi): Boolean =
//        oldItem.id == newItem.id && newItem.isClicked == oldItem.isClicked
//
//    override fun areContentsTheSame(oldItem: VideoUi, newItem: VideoUi): Boolean =
//        oldItem == newItem
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id
                && oldList[oldItemPosition].isClicked == newList[newItemPosition].isClicked

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

}