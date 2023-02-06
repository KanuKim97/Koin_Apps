package com.example.koin_apps.viewModel.activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.di.AppRepository
import com.example.koin_apps.data.database.tables.CoinEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class SelectViewModel @Inject constructor(
    private val repos: AppRepository
): ViewModel() {
    private val _coinTitleList = MutableLiveData<List<String?>?>()
    private val _selectedCoinTitle = MutableLiveData<List<String>>()

    val coinTitleList: LiveData<List<String?>?>
        get() = _coinTitleList
    val selectedTitleCoin: LiveData<List<String>>
        get()= _selectedCoinTitle

    init { _coinTitleList.value = null }
    private var coinListElement: MutableList<String> = mutableListOf()

    /* Store Selected data function
    * private val storeCoinTitleJob: Job = viewModelScope.launch { repos.addCoinList(coinListElement) }
    */
    private val tickerTitleJob: Job = viewModelScope.launch {
        val tickerTitleResponse = repos.getTickerAll()

        if (tickerTitleResponse.isSuccessful && tickerTitleResponse.body() != null) {
            val coinTitleList = tickerTitleResponse.body()?.data?.keys?.toList()
            _coinTitleList.postValue(coinTitleList)
        }
    }

    fun getCoinTitle() = tickerTitleJob.start()
    fun setSelectedData(selectedItem: List<String>) { _selectedCoinTitle.value = selectedItem }
    fun getCoinTitleData(TitleElements: MutableList<String>) { _selectedCoinTitle.postValue(TitleElements) }
    fun storeCoinTitleData() =
        try { /* store coinTitle Job */ } catch (e: NullPointerException) { e.printStackTrace() }
}






