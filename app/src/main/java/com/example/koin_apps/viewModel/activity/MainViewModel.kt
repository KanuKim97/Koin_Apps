package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.koin_apps.data.remote.model.mainViewTicker.MainTickerData as MainTickerData

class MainViewModel(private val repos: AppRepository): ViewModel() {
    private val _tickerLiveData = MutableLiveData<MainTickerData>()
    private lateinit var _readAllCoinData: LiveData<List<CoinEntity>>

    val readAllCoinData: LiveData<List<CoinEntity>>
        get() = _readAllCoinData
    val tickerLiveData: LiveData<MainTickerData>
        get() = _tickerLiveData

    init { viewModelScope.launch { _readAllCoinData = repos.readAllData() } }

    fun getTickerPrice(path: List<CoinEntity>) {
        for(elements in path) {
            repos.getTicker(elements.coinTitle).enqueue(object: Callback<TickerRoot> {
                override fun onResponse(
                    call: Call<TickerRoot>,
                    response: Response<TickerRoot>
                ) {
                    when(response.code()) {
                        200 -> {
                            try {
                                setResponseData(elements.coinTitle, response.body())
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

    }

    private fun setResponseData(
        coinTitle: String,
        responseBody: TickerRoot?
    ) {
        if (responseBody != null) {
            _tickerLiveData.value =
                MainTickerData(
                    coinTitle = coinTitle,
                    ticker_24H_FluctateRate = responseBody.data["fluctate_rate_24H"] as String,
                    ticker_24H_Fluctate = responseBody.data["fluctate_24H"] as String,
                    ticker_Prev_Closing_Price = responseBody.data["prev_closing_price"] as String,
                    ticker_24H_Units_Traded = responseBody.data["units_traded_24H"] as String,
                    ticker_24H_Acc_Trade_Value = responseBody.data["acc_trade_value_24H"] as String
                )
        } else { throw NullPointerException("Response Data is Null or Empty") }
    }

}