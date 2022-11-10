package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.remote.model.ticker.TickerData
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repos: AppRepository): ViewModel() {
    private val _tickerLiveData = MutableLiveData<TickerData?>()

    val tickerLiveData: LiveData<TickerData?>
        get() = _tickerLiveData

    init { _tickerLiveData.value = null }

    fun getTicker(path: String) {
        repos.getTicker(path).enqueue(object: Callback<TickerRoot> {
            override fun onResponse(
                call: Call<TickerRoot>,
                response: Response<TickerRoot>
            ) {
                when(response.code()) {
                    200 -> {
                        try {
                            val coinTickerDesc = response.body()

                            Log.d("ticker", "$coinTickerDesc")
                        } catch (e: NullPointerException) {
                            throw NullPointerException("Response Data is Null or Empty")
                        }
                    }

                    400 -> {
                        val errJsonObj: JSONObject

                        try{
                            errJsonObj = JSONObject(response.errorBody()?.string()!!)
                            val responseErrCode = errJsonObj.getString("status")
                            val responseErrMsg = errJsonObj.getString("message")

                            Log.d("400 Error", "$responseErrCode: $responseErrMsg")
                        } catch (e: JSONException) { e.printStackTrace() }
                    }
                }
            }

            override fun onFailure(call: Call<TickerRoot>, t: Throwable) { t.printStackTrace() }
        })
    }

    override fun onCleared() {
        super.onCleared()
        _tickerLiveData.value = null
    }

  /*
    fun updateKoinTicker(
        responseTicker: TickerRoot?
    ){
       if(responseTicker != null) {
           _tickerLiveData.value =
               TickerData(
                   responseTicker.status,
                   responseTicker.message,
                   responseTicker.data["opening_price"].toString(),
                   responseTicker.data["closing_price"].toString(),
                   responseTicker.data["min_price"].toString(),
                   responseTicker.data["max_price"].toString(),
                   responseTicker.data["units_traded"].toString(),
                   responseTicker.data["acc_trade_value"].toString(),
                   responseTicker.data["prev_closing_price"].toString(),
                   responseTicker.data["units_traded_24H"].toString(),
                   responseTicker.data["acc_trade_value_24H"].toString(),
                   responseTicker.data["fluctate_24H"].toString(),
                   responseTicker.data["fluctate_rate_24H"].toString(),
                   responseTicker.data["date"].toString()
               )
       } else { throw NullPointerException("response Data is Empty") }
    }

    fun updateErrorTicker(
        inputErrorCode: String,
        inputErrorMsg: String
    ){
        _tickerLiveData.value =
            TickerData(
                inputErrorCode,
                inputErrorMsg,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
            )
    }
    */

}