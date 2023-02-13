package com.wayne.taipeiair.webservice.json

import com.squareup.moshi.Json

data class RecordJson(
    @Json(name = "records")
    val cities: List<City> = emptyList()
)

data class City(
    @Json(name = "item1")
    val yearMonth: String = "",
    @Json(name = "item2")
    val name: String = "",
    @Json(name = "value1")
    val value1: String = "",
    @Json(name = "value2")
    val value2: String = "",
    @Json(name = "value3")
    val value3: String = "",
    @Json(name = "value4")
    val value4: String = "",
    @Json(name = "value5")
    val value5: String = "",
    @Json(name = "value6")
    val value6: String = "",
    @Json(name = "value7")
    val value7: String = ""
)
