package com.example.mymovieapp.data.mappers.person

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.mappers.movie.MovieListMapper
import com.example.mymovieapp.data.network.model.response.person.Person
import com.example.mymovieapp.domain.models.person.PersonModel

class PersonListMapper(private val mapper: MovieListMapper = MovieListMapper()) :
    Mapper<List<Person>, List<PersonModel>>(
        fromData = {
            it.map { person ->
                PersonModel(
                    profile_path = person.profile_path ?: "",
                    adult = person.adult,
                    id = person.id,
                    known_for = mapper.mapData(person.known_for),
                    name = person.name,
                    popularity = person.popularity
                )
            }
        }
    )