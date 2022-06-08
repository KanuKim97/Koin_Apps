package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.R
import com.example.koin_apps.data.remote.RetrofitRepo
import com.example.koin_apps.data.remote.model.ticker.TickerRoot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TradeViewModel: ViewModel() {
    private val _tradeLiveData = MutableLiveData<Map<String, Any?>?>()

    val tradeLiveData: LiveData<Map<String, Any?>?>
        get() = _tradeLiveData


    init { _tradeLiveData.value = null }

    override fun onCleared() {
        super.onCleared()
        _tradeLiveData.value = null
    }

    fun updateKoinTrade(
        inputList: MutableMap<String, Any?>
    ) {
        _tradeLiveData.value = inputList
    }

}