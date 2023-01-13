package com.example.mymovieapp.data.mappers.person

import com.example.mymovieapp.data.models.person.PersonDetailsData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain

class MapDataPersonDetailsToDomain : Mapper<PersonDetailsData, PersonDetailsDomain> {
    override fun map(from: PersonDetailsData) = from.run {
        PersonDetailsDomain(
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