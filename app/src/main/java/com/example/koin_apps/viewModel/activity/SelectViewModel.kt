package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.repository.ApiRepository
import com.example.koin_apps.data.di.repository.CoinTitleDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectViewModel @Inject constructor(
    private val ApiRepo: ApiRepository,
    private val coinDBRepo: CoinTitleDBRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _coinTitleList = MutableLiveData<List<String?>?>()
    private val _selectedCoinTitle = MutableLiveData<List<String>>()
    private var coinListElement: MutableList<String> = mutableListOf()

    private val tickerTitleJob: Job = viewModelScope.launch {
        val tickerTitleResponse = ApiRepo.getTickerAll()

        if (tickerTitleResponse.isSuccessful && tickerTitleResponse.body() != null) {
            val coinTitleList = tickerTitleResponse.body()?.data?.keys?.toList()
            _coinTitleList.postValue(coinTitleList)
        }
    }

    private val storeCoinTitle: Job = viewModelScope.launch(ioDispatcher) {
        for (listElement in coinListElement) { coinDBRepo.insertCoinTitle(CoinEntity(listElement)) }
    }

    val coinTitleList: LiveData<List<String?>?>
        get() = _coinTitleList
    val selectedTitleCoin: LiveData<List<String>>
        get()= _selectedCoinTitle

    fun getCoinTitle() = tickerTitleJob.start()
    fun storeCoinTitleData() = storeCoinTitle.start()
    fun setSelectedData(selectedItem: List<String>) { _selectedCoinTitle.postValue(selectedItem) }
    fun getCoinTitleData(titleElements: MutableList<String>) { _selectedCoinTitle.postValue(titleElements) }

    override fun onCleared() {
        super.onCleared()
        tickerTitleJob.cancel()
        storeCoinTitle.cancel()
    }
}






