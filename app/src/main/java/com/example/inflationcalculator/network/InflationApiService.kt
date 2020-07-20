package com.example.inflationcalculator.network

import com.example.inflationcalculator.data.HistoricalDataSources
import com.example.inflationcalculator.data.HistoricalInflationData
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

private const val BASE_URL = "https://api.bls.gov/publicAPI/v2/"

//moshi builder for retrofit to convert json
val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit builder to build a retrofit object using moshi converter
val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface InflationApiService {
    @Headers("Content-type: application/json")
    @POST
    fun getInflationData(@Body requestBody: RequestBody): Deferred<List<HistoricalInflationData>>

    @GET
    fun getHistoricalDatasource(): Deferred<HistoricalDataSources>
}

//exposes retrofit service to be used publicly
object InflationApi {
    val retrofitService: InflationApiService by lazy {
        retrofit.create(InflationApiService::class.java)
    }
}