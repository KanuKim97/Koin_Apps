package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TradeViewModel: ViewModel() {
    private val _tradeLiveData = MutableLiveData<Map<String, Any?>?>()

    val tradeLiveData: LiveData<Map<String, Any?>?>
        get() = _tradeLiveData

    val ticker = RetrofitRepo.getTickerSingleton("BTC")

    init {
        _tradeLiveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _tradeLiveData.value = null
    }

    fun updateKoinTrade(
        inputList: MutableMap<String, Any?>
    ) {
        _tradeLiveData.value = inputList

    }

    //Test Code
    fun getTradePrice(Address: String){
        ticker.enqueue(object: Callback<TickerRoot>{
            override fun onResponse(call: Call<TickerRoot>, response: Response<TickerRoot>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<TickerRoot>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

}