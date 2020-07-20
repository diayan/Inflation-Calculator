package com.example.inflationcalculator.data

import com.squareup.moshi.Json

data class HistoricalInflationData (
    @Json(name = "seriesid")
    val seriesId: String,
    @Json(name = "startyear")
    val startYear: String,
    @Json(name = "endyear")
    val endYear: String
)