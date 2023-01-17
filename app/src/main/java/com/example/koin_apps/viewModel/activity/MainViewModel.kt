package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.*
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.remote.model.ticker.mainViewTicker.MainTickerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainViewModel(private val repos: AppRepository): ViewModel() {
    private val _tickerLiveData = MutableLiveData<ArrayList<MainTickerData>>()
    private val _responseData = ArrayList<MainTickerData>()

    val tickerLiveData: LiveData<ArrayList<MainTickerData>>
        get() = _tickerLiveData

    lateinit var readAllCoinData: LiveData<List<CoinEntity>>

    init { viewModelScope.launch (Dispatchers.IO) { readAllCoinData = repos.readAllData() } }

    fun getPriceTicker(coinEntity: List<CoinEntity>) =
        coinEntity.forEach { elements -> getBithumbTicker(elements.coinTitle) }

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