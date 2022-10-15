package com.example.mymovieapp.data.mappers


open class Mapper<Data, Domain>(
    private val fromData: (Data) -> Domain = { TODO() },
    private val fromDomain: ((Domain) -> Data) = { TODO() }
) {
    fun mapData(t: Data): Domain = fromData(t)

    fun mapDomain(e: Domain): Data = fromDomain.invoke(e)
}
