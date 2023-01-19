package com.example.mymovieapp.presentation.ui.adapters

interface RvClickListener<T> {
    fun onItemClick(item: T) = Unit
    fun onStarClick(item: T) = Unit
}