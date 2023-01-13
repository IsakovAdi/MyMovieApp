package com.example.mymovieapp.presentation.mappers.person

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi

class MapDomainPersonDetailsToUi : Mapper<PersonDetailsDomain, PersonDetailsUi> {
    override fun map(from: PersonDetailsDomain) = from.run {
        PersonDetailsUi(
            known_for_department = known_for_department,
            also_known_as = also_known_as.map { it },
            biography = biography,
            birthday = birthday,
            deathDay = deathDay,
            gender = gender,
            id = id,
            name = name,
            popularity = popularity,
            place_of_birth = place_of_birth,
            profile_path = profile_path
        )
    }
}