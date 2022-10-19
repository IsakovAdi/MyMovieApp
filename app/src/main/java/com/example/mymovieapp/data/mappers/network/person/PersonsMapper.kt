package com.example.mymovieapp.data.mappers.network.person

import com.example.mymovieapp.data.mappers.Mapper
import com.example.mymovieapp.data.network.model.response.person.Persons
import com.example.mymovieapp.domain.models.person.PersonsModel

class PersonsMapper(private val mapper: PersonListMapper = PersonListMapper()) :
    Mapper<Persons, PersonsModel>(
        fromData = {
            PersonsModel(
                page = it.page,
                persons = mapper.mapData(it.persons),
                total_results = it.total_results,
                total_pages = it.total_pages
            )
        }
    )