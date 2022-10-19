package com.example.mymovieapp.data.mappers.network.person

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.person.PersonDetails
import com.example.mymovieapp.domain.models.person.PersonDetailsModel

class PersonDetailsMapper : Mapper<PersonDetails, PersonDetailsModel>(
    fromData = { person ->
        PersonDetailsModel(
            known_for_department = person.known_for_department,
            also_known_as = person.also_known_as.map { it },
            biography = person.biography,
            birthday = person.birthday,
            deathDay = person.deathDay,
            gender = if (person.gender == 1) "Male" else "Female",
            id = person.id,
            name = person.name,
            popularity = person.popularity,
            place_of_birth = person.place_of_birth,
            profile_path = person.profile_path
        )
    }
)