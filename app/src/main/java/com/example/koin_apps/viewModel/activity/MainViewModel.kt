package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.*
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import com.example.koin_apps.data.remote.model.mainViewTicker.MainTickerData as MainTickerData

class MainViewModel(private val repos: AppRepository): ViewModel() {
    private lateinit var _readAllCoinData: LiveData<List<CoinEntity>>
    private val _tickerLiveData = MutableLiveData<ArrayList<MainTickerData>>()

    val readAllCoinData: LiveData<List<CoinEntity>>
        get() = _readAllCoinData
    val tickerLiveData: LiveData<ArrayList<MainTickerData>>
        get() = _tickerLiveData

    init { viewModelScope.launch { _readAllCoinData = repos.readAllData() } }

    fun getPriceTicker(coinEntity: List<CoinEntity>) {
        for (elements in coinEntity) { getBithumbTicker(elements.coinTitle) }
    }

    private fun getBithumbTicker(element: String) {
        viewModelScope.launch (Dispatchers.IO) {
            val response = repos.getTicker(element)

            when (response.code()) {
                200 -> {
                    try {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            // TODO : 채워넣기
                        }
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

    }

}