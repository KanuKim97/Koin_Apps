package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.koin_apps.data.remote.model.ticker.TickerData
import com.example.koin_apps.data.remote.model.ticker.TickerList

class MainViewModel: ViewModel() {
    //Code Line
    //ToDo ("Rebuild MainViewModel -> LiveData to ArrayList")

    private val _tickerLiveData = MutableLiveData<List<TickerList>>()
    private val _koinTickerArray = arrayListOf<TickerList>()

    fun addKoinTicker(
        input: TickerList
    ){

    }

    fun updateKoinTicker(
        input: TickerList
    ){

    }


    /*
    private val _openingKoinValue = MutableLiveData<Int?>()
    private val _closingKoinValue = MutableLiveData<Int?>()
    private val _minPrice = MutableLiveData<Int?>()
    private val _maxPrice = MutableLiveData<Int?>()
    private val _unitTraded = MutableLiveData<Double?>()
    private val _accTradeValue = MutableLiveData<Double?>()


    val resultKoinValue: LiveData<Int?>
        get() = _openingKoinValue


    init {
        _openingKoinValue.value = 0
        _closingKoinValue.value = 0
        _minPrice.value = 0
        _maxPrice.value = 0
        _unitTraded.value = 0.0
        _accTradeValue.value = 0.0
    }

    fun updateValue(
        input: Int
    ){
        _openingKoinValue.value = input
    }
    */
}