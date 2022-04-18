package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _resultKoinValue = MutableLiveData<Int?>()

    val resultKoinValue: LiveData<Int?>
        get() = _resultKoinValue


    init {
        _resultKoinValue.value = 0
    }

    fun updateValue(input: Int?){
        _resultKoinValue.value = input
    }
}