package com.example.koin_apps.viewModel.activity

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


}