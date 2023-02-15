package com.wayne.taipeiair.webservice

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Streaming

interface OpenDataInterface {
    @Streaming
    @GET("stat_p_116?api_key=e8dd42e6-9b8b-43f8-991e-b3dee723a52d&limit=1000&sort=ImportDate%20desc&format=JSON")
    fun getDataAsync(): Deferred<ResponseBody>
}