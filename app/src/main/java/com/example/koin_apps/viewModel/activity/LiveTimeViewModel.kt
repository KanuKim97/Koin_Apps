package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.remote.model.ticker.TickerData
import com.example.koin_apps.data.remote.model.ticker.liveViewTicker.LiveTickerData
import kotlinx.coroutines.*
import org.json.JSONException
import org.json.JSONObject


class LiveTimeViewModel(private val repos: AppRepository): ViewModel() {
    private val _tickerLiveViewData = MutableLiveData<LiveTickerData>()

    val tickerLiveViewData: LiveData<LiveTickerData>
        get() = _tickerLiveViewData

    fun getTickerLive(path: String) {
        val tickerResponse = viewModelScope.launch {
            while (true) {
                delay(3000L)
                Log.d("Coroutine","Transaction is Running")

                val response = repos.getTicker(path)

                when (response.code()) {
                    200 -> {
                        try {
                            val responseBody = response.body()

                            if (responseBody != null) {
                                _tickerLiveViewData.postValue(
                                    LiveTickerData(
                                        responseBody.data["closing_price"].toString(),
                                        responseBody.data["fluctate_24H"].toString(),
                                        responseBody.data["fluctate_rate_24H"].toString()
                                    )
                                )
                            }
                        } catch (e: NullPointerException) {
                            throw NullPointerException("Response Data is Null or Empty")
                        }
                    }
                    400-> {
                        val errJsonObj: JSONObject

                        try {
                            errJsonObj = JSONObject(response.errorBody()?.string()!!)
                            val responseErrCode = errJsonObj.getString("status")
                            val responseErrMsg = errJsonObj.getString("message")

                            Log.d("400 Error", "$responseErrCode: $responseErrMsg")
                        } catch (e: JSONException) { e.printStackTrace() }
                    }
                }

            }
        }

        tickerResponse.start()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}