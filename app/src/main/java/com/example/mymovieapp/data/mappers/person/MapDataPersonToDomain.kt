package com.example.mymovieapp.data.mappers.person

import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.models.person.PersonData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.person.PersonDomain

class MapDataPersonToDomain(
    private val mapper: Mapper<List<MovieData>, List<MovieDomain>>,
) : Mapper<PersonData, PersonDomain> {
    override fun map(from: PersonData) = from.run {
        PersonDomain(
            profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapper.map(known_for),
            name = name,
            popularity = popularity
        )
    }
}