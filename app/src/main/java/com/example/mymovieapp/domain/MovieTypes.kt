package com.example.mymovieapp.domain

object MovieTypes {
    fun getMovieTypesEn(): ArrayList<String> {
        val list = ArrayList<String>()
        list.add("Popular")
        list.add("Now playing")
        list.add("Top rated")
        list.add("Upcoming")
        return list
    }

    fun getMovieTypesRu():ArrayList<String>{
        val list = ArrayList<String>()
        list.add("Популярные")
        list.add("На экранах")
        list.add("Рейтинговые")
        list.add("Ожидающиеся")
        return list
    }
}