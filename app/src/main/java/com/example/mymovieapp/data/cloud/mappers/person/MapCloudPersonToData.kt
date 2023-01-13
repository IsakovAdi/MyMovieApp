package com.example.mymovieapp.data.cloud.mappers.person

import com.example.mymovieapp.data.cloud.model.movie.MovieCloud
import com.example.mymovieapp.data.cloud.model.person.PersonCloud
import com.example.mymovieapp.data.models.movie.MovieData
import com.example.mymovieapp.data.models.person.PersonData
import com.example.mymovieapp.domain.Mapper

class MapCloudPersonToData(private val mapper: Mapper<List<MovieCloud>, List<MovieData>>) :
    Mapper<PersonCloud, PersonData> {
    override fun map(from: PersonCloud) = from.run {
        PersonData(
            profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapper.map(known_for),
            name = name,
            popularity = popularity
        )
    }
}