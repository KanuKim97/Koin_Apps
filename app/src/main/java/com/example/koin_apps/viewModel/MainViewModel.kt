package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.ticker.TickerList

class MainViewModel: ViewModel() {

    private val _tickerLiveData = MutableLiveData<List<TickerList>?>()
    private val _koinTickerArray = arrayListOf<TickerList>()

    val tickerLiveData: LiveData<List<TickerList>?>
        get() = _tickerLiveData

    init {
        _tickerLiveData.value = null
    }

    override fun onCleared() {
        super.onCleared()
        _tickerLiveData.value = null

    }

    fun updateKoinTicker(
        input: TickerList
    ){
        _koinTickerArray.add(input)
        _tickerLiveData.value = _koinTickerArray

        _koinTickerArray.remove(input)
    }
}