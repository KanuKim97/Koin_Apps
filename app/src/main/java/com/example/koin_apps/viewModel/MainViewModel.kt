package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.ticker.TickerList

class MainViewModel: ViewModel() {
    private val _tickerLiveData = MutableLiveData<Map<String, Any?>?>()

    val tickerLiveData: LiveData<Map<String, Any?>?>
        get() = _tickerLiveData

    init {
        _tickerLiveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _tickerLiveData.value = null
    }

    fun updateKoinTicker(
        input: MutableMap<String, Any?>
    ){
        _tickerLiveData.value = input

    }
}