package com.example.inflationcalculator.data

import com.squareup.moshi.Json

data class HistoricalInflationData(
    @Json(name = "seriesid")
    val seriesId: Array<String>,
    @Json(name = "startyear")
    val startYear: String,
    @Json(name = "endyear")
    val endYear: String,
    @Json(name = "catalog")
    val catalog: Boolean,
    @Json(name = "calculations")
    val calculations: Boolean,
    @Json(name = "annualaverage")
    val annualaverage: Boolean,
    @Json(name = "registrationkey")
    val registrationkey: String
)
