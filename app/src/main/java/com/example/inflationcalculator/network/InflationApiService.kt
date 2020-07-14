package com.example.inflationcalculator.network

import com.example.inflationcalculator.data.InflationData
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.POST

private const val BASE_URL = "https://api.bls.gov/publicAPI/v2/"

//moshi builder for retrofit to convert json
val moshi: Moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

//retrofit builder to build a retrofit object using moshi converter
val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface InflationApiService {
    @POST
    fun postRequest(): Deferred<InflationData>
}

//exposes retrofit service to be used publicly
object InflationApi {
    val retrofitService: InflationApiService by lazy {
        retrofit.create(InflationApiService::class.java)
    }
}