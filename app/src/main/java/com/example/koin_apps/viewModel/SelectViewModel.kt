package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.koin_apps.data.AppRepository
import com.example.koin_apps.data.entities.DBRepository
import com.example.koin_apps.data.entities.db.CoinEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SelectViewModel: ViewModel() {
    private val _selectKoinList = MutableLiveData<List<String>?>()

    // add Room Code
    private val coinDBRepo: DBRepository

    val selectKoinList: LiveData<List<String>?>
        get() = _selectKoinList

    init {
        _selectKoinList.value = null

        // add Room Code
        val coinDBDao = AppRepository.createAppDBClient().coinTitleDao()
        coinDBRepo = DBRepository(coinDBDao)
    }

    // add Room Code
    fun addCoinOnDB(coinTitle: CoinEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            coinDBRepo.addUser(coinTitle)
        }
    }

    fun updateSelectValue(
        koinTitleKeySet: Set<String?>?
    ) {
        val coinTitleList: List<String?>? = koinTitleKeySet?.toList()

        _selectKoinList.value = coinTitleList as List<String>
    }

    fun updateResponseError(
        inputErrorCode: String,
        inputErrorMsg: String
    ){
        val errorBody: List<String> = listOf(inputErrorCode, inputErrorMsg)
        _selectKoinList.value = errorBody
    }

    override fun onCleared() {
        super.onCleared()
        _selectKoinList.value = null
    }

}