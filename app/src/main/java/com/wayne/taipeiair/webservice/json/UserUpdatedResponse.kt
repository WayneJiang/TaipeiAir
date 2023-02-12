package com.wayne.taipeiair.webservice.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserUpdatedResponse(
    @Json(name = "updatedAt")
    val updatedAt: String = ""
)
