package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.*
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.model.ticker.mainViewTicker.MainTickerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(private val repos: AppRepository): ViewModel() {
    private lateinit var _readAllCoinData: LiveData<List<CoinEntity>>
    private val _tickerLiveData = MutableLiveData<ArrayList<MainTickerData>>()
    private val _responseData = ArrayList<MainTickerData>()

    val readAllCoinData: LiveData<List<CoinEntity>>
        get() = _readAllCoinData
    val tickerLiveData: LiveData<ArrayList<MainTickerData>>
        get() = _tickerLiveData

    init { viewModelScope.launch (Dispatchers.IO) { _readAllCoinData = repos.readAllData() } }

    fun getPriceTicker(coinEntity: List<CoinEntity>) {
        for (elements in coinEntity) {
            getBithumbTicker(elements.coinTitle)
        }
    }

    private fun getBithumbTicker(element: String) {
        viewModelScope.launch {
                val response = repos.getTicker(element)
                when (response.code()) {
                    200 -> {
                        val responseBody = response.body()

                        if (responseBody != null) {
                            _responseData.add(
                                MainTickerData(
                                    element,
                                    responseBody.data["fluctate_24H"].toString(),
                                    responseBody.data["fluctate_rate_24H"].toString()
                                )
                            )
                            _tickerLiveData.postValue(_responseData)
                        }
                    }
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}