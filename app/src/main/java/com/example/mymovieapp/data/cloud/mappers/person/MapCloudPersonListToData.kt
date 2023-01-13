package com.example.mymovieapp.data.cloud.mappers.person

import com.example.mymovieapp.data.cloud.model.person.PersonCloud
import com.example.mymovieapp.data.models.person.PersonData
import com.example.mymovieapp.domain.Mapper

class MapCloudPersonListToData(private val mapper: Mapper<PersonCloud, PersonData>) :
    Mapper<List<PersonCloud>, List<PersonData>> {
    override fun map(from: List<PersonCloud>) = from.run {
        map(mapper::map)
    }
}