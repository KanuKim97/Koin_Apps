package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SelectViewModel: ViewModel() {
    private val _selectKoinList = MutableLiveData<Array<String>?>()

    val selectKoinList: LiveData<Array<String>?>
        get() = _selectKoinList

    init { _selectKoinList.value = null}

    override fun onCleared() {
        super.onCleared()
    }

    fun updateSelectValue(
        inputList: MutableMap<String, Any>
    ) {

    }

}