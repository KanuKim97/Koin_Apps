package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.model.ticker.TickerData
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val repos: AppRepository): ViewModel() {
    private val _tickerLiveData = MutableLiveData<TickerData?>()
    private val _readAllCoinData: LiveData<List<CoinEntity>>

    val readAllCoinData: LiveData<List<CoinEntity>>
        get() = _readAllCoinData
    val tickerLiveData: LiveData<TickerData?>
        get() = _tickerLiveData

    init {
        _tickerLiveData.value = null
        _readAllCoinData = repos.readAllData()
    }


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

}