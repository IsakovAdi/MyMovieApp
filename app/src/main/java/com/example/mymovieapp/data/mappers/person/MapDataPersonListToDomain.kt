package com.example.mymovieapp.data.mappers.person

import com.example.mymovieapp.data.models.person.PersonData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.person.PersonDomain

class MapDataPersonListToDomain(
    private val mapper: Mapper<PersonData, PersonDomain>,
) : Mapper<List<PersonData>, List<PersonDomain>> {
    override fun map(from: List<PersonData>) = from.run {
        map(mapper::map)
    }
}