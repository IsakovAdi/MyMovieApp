package com.example.mymovieapp.data.mappers.person

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.mappers.movie.MovieListMapper
import com.example.mymovieapp.data.network.model.response.person.Person
import com.example.mymovieapp.data.network.model.response.person.PersonDetails
import com.example.mymovieapp.domain.models.movie.MovieModel
import com.example.mymovieapp.domain.models.person.PersonDetailsModel
import com.example.mymovieapp.domain.models.person.PersonModel

class PersonDetailsMapper() : Mapper<PersonDetails, PersonDetailsModel>(
    fromData = { person ->
        PersonDetailsModel(
            adult = person.adult,
            known_for_department = person.known_for_department,
            deathDay = person.deathDay,
            id = person.id,
            name = person.name,
            also_known_as = person.also_known_as.map { it },
            gender = person.gender,
            biography = person.biography,
            popularity = person.popularity,
            place_of_birth = person.place_of_birth,
            profile_path = person.profile_path,
            imdb_id = person.imdb_id,
            homepage = person.homepage
        )
    }
)