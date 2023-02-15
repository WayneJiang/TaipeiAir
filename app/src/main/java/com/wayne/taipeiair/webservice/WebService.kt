package com.wayne.taipeiair.webservice

import android.net.Uri
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.wayne.taipeiair.repository.Repository.insert
import com.wayne.taipeiair.repository.entity.CityEntity
import com.wayne.taipeiair.webservice.json.RecordJson
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

object WebService {
    private val mOkHttpClient by lazy {
        OkHttpClient.Builder().apply {
            retryOnConnectionFailure(true)
            connectTimeout(300, TimeUnit.SECONDS)
            writeTimeout(300, TimeUnit.SECONDS)
            readTimeout(300, TimeUnit.SECONDS)
            addInterceptor(HttpLoggingInterceptor(HttpLogger()).apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    private class HttpLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            val maxLength = 2000
            var start = 0
            var end = maxLength
            val logLength = message.length
            for (index in 0 until 100) {
                if (logLength > end) {
                    Log.d("Wayne", Uri.decode(message.substring(start, end)))
                    start = end
                    end += maxLength
                } else {
                    Log.d("Wayne", Uri.decode(message.substring(start, logLength)))
                    break
                }
            }
        }
    }

    private val mOpenDataInterface by lazy {
        Retrofit.Builder().addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("https://data.epa.gov.tw/api/v2/")
            .client(mOkHttpClient)
            .build()
            .create(OpenDataInterface::class.java)
    }

    private val mCoroutineScope = object : CoroutineScope {
        override val coroutineContext: CoroutineContext
            get() = Dispatchers.IO
    }

    private lateinit var mJob: Job

    fun requestData(callback: (Boolean) -> Unit) {
        mJob = mCoroutineScope.launch(
            CoroutineExceptionHandler { _, throwable ->
                Log.d("Wayne", Log.getStackTraceString(throwable))

                mCoroutineScope.launch {
                    withContext(Dispatchers.Main) {
                        callback.invoke(false)
                    }
                }

                mJob.cancel()
            }
        ) {
            val bufferedSource = mOpenDataInterface.getDataAsync().await()

            val adapter =
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                    .adapter(RecordJson::class.java)

            bufferedSource.byteStream().bufferedReader().use {
                val recordJson = adapter.fromJson(it.readText())

                recordJson?.cities?.forEach { city ->
                    val yearMonth = city.yearMonth.split(" ")
                    val year = (yearMonth[0].split("年")[0]).toInt() * 100
                    val month =
                        if (yearMonth.size > 1) {
                            (yearMonth[1].split("月")[0]).toInt()
                        } else {
                            0
                        }

                    CityEntity(
                        city.yearMonth.replace(" ", ""),
                        (year + month),
                        city.name,
                        city.value1.toDoubleOrNull() ?: -9999.0,
                        city.value2.toDoubleOrNull() ?: -9999.0,
                        city.value3.toDoubleOrNull() ?: -9999.0,
                        city.value4.toDoubleOrNull() ?: -9999.0,
                        city.value5.toDoubleOrNull() ?: -9999.0,
                        city.value6.toDoubleOrNull() ?: -9999.0,
                        city.value7.toDoubleOrNull() ?: -9999.0
                    ).insert()
                }
            }

            withContext(Dispatchers.Main) {
                callback.invoke(true)
            }
        }
    }
}