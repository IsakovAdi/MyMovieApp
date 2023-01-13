package com.example.mymovieapp.data.mappers.person

import com.example.mymovieapp.data.models.person.PersonData
import com.example.mymovieapp.data.models.person.PersonsResponseData
import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.person.PersonDomain
import com.example.mymovieapp.domain.models.person.PersonsResponseDomain

class MapDataPersonResponseToDomain(
    private val mapper: Mapper<List<PersonData>, List<PersonDomain>>,
) : Mapper<PersonsResponseData, PersonsResponseDomain> {
    override fun map(from: PersonsResponseData) = from.run {
        PersonsResponseDomain(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}