package com.example.mymovieapp.data.cloud.mappers.person

import com.example.mymovieapp.data.cloud.model.person.PersonCloud
import com.example.mymovieapp.data.cloud.model.person.PersonsResponseCloud
import com.example.mymovieapp.data.models.person.PersonData
import com.example.mymovieapp.data.models.person.PersonsResponseData
import com.example.mymovieapp.domain.Mapper

class MapCloudPersonResponseToData(private val mapper: Mapper<List<PersonCloud>, List<PersonData>>) :
    Mapper<PersonsResponseCloud, PersonsResponseData> {
    override fun map(from: PersonsResponseCloud) = from.run {
        PersonsResponseData(
            page = page,
            persons = mapper.map(persons),
            total_results = total_results,
            total_pages = total_pages
        )
    }
}