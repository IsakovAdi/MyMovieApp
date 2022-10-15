package com.example.mymovieapp.domain.models

data class ResponseState(
    var totalPage: Int,
    var page: Int = 1,
    var previousPage: Int,
    var nextPage: Int,
    var isHasPreviousPage: Boolean,
    var isHasNextPage: Boolean
)