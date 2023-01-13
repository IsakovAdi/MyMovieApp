package com.example.mymovieapp.data.cloud.mappers.person

import com.example.mymovieapp.data.cloud.model.person.PersonDetailsCloud
import com.example.mymovieapp.data.models.person.PersonDetailsData
import com.example.mymovieapp.domain.Mapper

class MapCloudPersonDetailsToData : Mapper<PersonDetailsCloud, PersonDetailsData> {
    override fun map(from: PersonDetailsCloud) = from.run {
        PersonDetailsData(
            known_for_department = known_for_department,
            also_known_as = also_known_as.map { it },
            biography = biography,
            birthday = birthday,
            deathDay = deathDay,
            gender = when (gender) {
                1 -> "Female"
                2 -> "Male"
                else -> {
                    "Unknown"
                }
            },
            id = id,
            name,
            popularity = popularity,
            place_of_birth = place_of_birth,
            profile_path = profile_path
        )
    }
}