package com.example.mymovieapp.data.cloud

object Endpoints {
    object Movie {
        const val POPULAR = "movie/popular"
        const val TOP_RATED = "movie/top_rated"
        const val UPCOMING = "movie/upcoming"
        const val NOW_PLAYING = "movie/now_playing"
        const val SEARCH_MOVIE = "search/movie"
        const val MOVIE_DETAILS = "movie/{movie_id}"
        const val SIMILAR = "movie/{movie_id}/similar"
        const val RECOMMENDATIONS = "movie/{movie_id}/recommendations"
    }

    object TRAILER {
        const val MOVIE_TRAILERS = "movie/{movie_id}/videos"
    }

    object Person {
        const val PERSON_POPULAR = "person/popular"
        const val PERSON_DETAILS = "person/{person_id}"
    }
}