package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.ticker.TickerData
import com.example.koin_apps.data.remote.model.ticker.TickerRoot

class MainViewModel: ViewModel() {
    private val _tickerLiveData = MutableLiveData<TickerData?>()

    val tickerLiveData: LiveData<TickerData?>
        get() = _tickerLiveData

    init { _tickerLiveData.value = null }

    override fun onCleared() {
        super.onCleared()
        _tickerLiveData.value = null
    }

    fun updateKoinTicker(
        responseTicker: TickerRoot?
    ){
        if(responseTicker?.status == "0000") {
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
        }

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

}