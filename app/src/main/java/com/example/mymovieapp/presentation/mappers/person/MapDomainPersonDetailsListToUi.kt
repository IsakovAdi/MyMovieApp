package com.example.mymovieapp.presentation.mappers.person

import com.example.mymovieapp.domain.Mapper
import com.example.mymovieapp.domain.models.person.PersonDetailsDomain
import com.example.mymovieapp.presentation.models.person.PersonDetailsUi

class MapDomainPersonDetailsListToUi(
    private val mapper: Mapper<PersonDetailsDomain, PersonDetailsUi>,
) : Mapper<List<PersonDetailsDomain>, List<PersonDetailsUi>> {
    override fun map(from: List<PersonDetailsDomain>) = from.run {
        map(mapper::map)
    }
}