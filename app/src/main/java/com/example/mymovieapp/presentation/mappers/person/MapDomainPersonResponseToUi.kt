package com.example.mymovieapp.presentation.mappers.person

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.person.PersonDomain
import com.example.mymovieapp.domain.models.person.PersonsResponseDomain
import com.example.mymovieapp.presentation.models.person.PersonUi
import com.example.mymovieapp.presentation.models.person.PersonsResponseUi

class MapDomainPersonResponseToUi(
    private val mapper: Mapper<List<PersonDomain>, List<PersonUi>>,
) : Mapper<PersonsResponseDomain, PersonsResponseUi> {
    override fun map(from: PersonsResponseDomain) = from.run {
        PersonsResponseUi(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}