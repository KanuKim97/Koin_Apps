package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.ticker.TickerData
import com.example.koin_apps.data.remote.model.ticker.TickerRoot

class TradeViewModel: ViewModel() {
    private val _tradeLiveData = MutableLiveData<TickerData?>()

    val tradeLiveData: LiveData<TickerData?>
        get() = _tradeLiveData


    init { _tradeLiveData.value = null }

    override fun onCleared() {
        super.onCleared()
        _tradeLiveData.value = null
    }

    fun updateKoinTrade(
        koinTradeList: TickerRoot?
    ) {
        if(koinTradeList != null) {
            _tradeLiveData.value =
                TickerData(
                    koinTradeList.status,
                    koinTradeList.message,
                    koinTradeList.data["opening_price"].toString(),
                    koinTradeList.data["closing_price"].toString(),
                    koinTradeList.data["min_price"].toString(),
                    koinTradeList.data["max_price"].toString(),
                    koinTradeList.data["units_traded"].toString(),
                    koinTradeList.data["acc_trade_value"].toString(),
                    koinTradeList.data["prev_closing_price"].toString(),
                    koinTradeList.data["units_traded_24H"].toString(),
                    koinTradeList.data["acc_trade_value_24H"].toString(),
                    koinTradeList.data["fluctate_24H"].toString(),
                    koinTradeList.data["fluctate_rate_24H"].toString(),
                    koinTradeList.data["date"].toString()
                )
        } else { throw NullPointerException("Response Data is Empty") }
    }

    fun updateErrorTicker(
        inputErrorCode: String,
        inputErrorMsg: String
    ){
        _tradeLiveData.value =
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