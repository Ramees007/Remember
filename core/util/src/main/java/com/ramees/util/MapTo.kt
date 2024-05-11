package com.ramees.util

interface MapTo<From, To> {
    fun mapTo(item: From): To
}

interface MapFrom<From, To> {
    fun mapFrom(item: To): From
}

interface Mapper<From, To> : MapTo<From, To>, MapFrom<From, To>

fun <From, To> List<From>.mapListTo(mapper: MapTo<From, To>) = map {
    mapper.mapTo(it)
}