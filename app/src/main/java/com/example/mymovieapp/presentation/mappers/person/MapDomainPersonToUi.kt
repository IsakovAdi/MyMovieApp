package com.example.mymovieapp.presentation.mappers.person

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.movie.MovieDomain
import com.example.mymovieapp.domain.models.person.PersonDomain
import com.example.mymovieapp.presentation.models.movie.MovieUi
import com.example.mymovieapp.presentation.models.person.PersonUi

class MapDomainPersonToUi(
    private val mapper: Mapper<List<MovieDomain>, List<MovieUi>>,
) : Mapper<PersonDomain, PersonUi> {
    override fun map(from: PersonDomain) = from.run {
        PersonUi(
            profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapper.map(known_for),
            name = name,
            popularity = popularity
        )
    }
}