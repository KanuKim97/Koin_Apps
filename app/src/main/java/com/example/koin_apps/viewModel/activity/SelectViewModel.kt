package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.database.tables.CoinEntity
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.repository.CoinRepository
import com.example.koin_apps.data.di.repository.CoinTitleDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectViewModel @Inject constructor(
    private val coinApiRepos: CoinRepository,
    private val coinDBRepo: CoinTitleDBRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _coinTitleList = MutableLiveData<List<String?>?>()
    val coinTitleList: LiveData<List<String?>?> get() = _coinTitleList

    fun loadTickerTitle() = viewModelScope.launch(ioDispatcher) {
        coinApiRepos.getTickerInfoALL().collect { _coinTitleList.postValue(it) }
    }

    fun storeTickerTitle(selectedCoinTitle: List<String>) = viewModelScope.launch(ioDispatcher) {
        for (listElement in selectedCoinTitle) {
            coinDBRepo.insertCoinTitle(CoinEntity(listElement))
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}