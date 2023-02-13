package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import com.example.koin_apps.data.di.AppRepository
import com.example.koin_apps.data.remote.model.ticker.liveViewTicker.LiveTickerData
import org.json.JSONException
import org.json.JSONObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LiveTimeViewModel @Inject constructor(
    private val repos: AppRepository
): ViewModel() {
    private val _tickerLiveViewData = MutableLiveData<LiveTickerData>()

    val tickerLiveViewData: LiveData<LiveTickerData>
        get() = _tickerLiveViewData

    fun getTickerLive(path: String) {
        val tickerResponse = viewModelScope.launch {
            while (true) {
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

                delay(3000L)
            }
        }

        tickerResponse.start()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}