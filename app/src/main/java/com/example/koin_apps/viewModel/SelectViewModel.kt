package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectViewModel: ViewModel() {
    private val _selectKoinList = MutableLiveData<List<String>?>()

    val selectedData = arrayListOf<String>()
    val selectKoinList: LiveData<List<String>?>
        get() = _selectKoinList

    init { _selectKoinList.value = null }

    override fun onCleared() {
        super.onCleared()
        _selectKoinList.value = null
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

}