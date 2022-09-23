package com.example.koin_apps.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectViewModel: ViewModel() {
    private val _selectKoinList = MutableLiveData<Set<String>?>()

    val selectKoinList: LiveData<Set<String>?>
        get() = _selectKoinList

    init { _selectKoinList.value = null}

    override fun onCleared() {
        super.onCleared()
        _selectKoinList.value = null
    }

    fun updateSelectValue(
        koinTitleKeySet: Set<String?>?
    ) {
        val coinTitleList: Array<String?> = koinTitleKeySet!!.toTypedArray()

        Log.d("Title: ", "$coinTitleList")
    }

    fun updateResponseError(
        inputErrorCode: String,
        inputErrorMsg: String
    ){
        val errorBody: Set<String> = setOf(inputErrorCode, inputErrorMsg)
        _selectKoinList.value = errorBody
    }

}