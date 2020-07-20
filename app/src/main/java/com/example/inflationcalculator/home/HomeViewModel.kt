package com.example.inflationcalculator.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.inflationcalculator.data.HistoricalInflationData
import com.example.inflationcalculator.network.InflationApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject

class HomeViewModel : ViewModel() {

    private val _historicalInflationData = MutableLiveData<List<HistoricalInflationData>>()

    val historicalInflationData: LiveData<List<HistoricalInflationData>>
        get() = _historicalInflationData

    //coroutine scope using job to be able to cancel
    private var viewModelJob = Job()

    //coroutine runs using the main ui dispatcher
    private val coroutineScope = CoroutineScope((viewModelJob + Dispatchers.Main))

    fun getInflationData() {
        coroutineScope.launch {
            val getInflationDataDeferred =
                InflationApi.retrofitService.getInflationData(requestBody)
            try {
                val listResult = getInflationDataDeferred.await()
                _historicalInflationData.value = listResult
            } catch (e: Exception) {
                _historicalInflationData.value = ArrayList()
            }
        }
    }


    val requestBody = RequestBody.create(
        MediaType.parse("application/json"),
        getJsonObject("CUUR0000SA0", "2000", "2020").toString()
    )


    private fun getJsonObject(seriesId: String, startYear: String, endYear: String): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("jsonrpc", "2.0")
        jsonObject.put("method", "core")
        jsonObject.put("id", "1000")

        val mJson = JSONObject()
        mJson.put("seriesid", seriesId)
        mJson.put("startyear", startYear)
        mJson.put("endyear", endYear)

        val jsonArray = JSONArray()
        jsonArray.put(mJson)

        jsonObject.put("params", jsonArray)
        Log.d("Params", jsonObject.toString())

        return jsonObject
    }
}
