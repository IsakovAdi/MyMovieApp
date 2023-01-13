package com.example.mymovieapp.presentation.mappers.person

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.person.PersonDomain
import com.example.mymovieapp.presentation.models.person.PersonUi

class MapDomainPersonListToUi(
    private val mapper: Mapper<PersonDomain, PersonUi>,
) : Mapper<List<PersonDomain>, List<PersonUi>> {
    override fun map(from: List<PersonDomain>) = from.run {
        map(mapper::map)
    }
}