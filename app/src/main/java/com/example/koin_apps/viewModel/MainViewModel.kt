package com.example.koin_application.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _resultKoinValue = MutableLiveData<Double>()

    val resultKoinValue: LiveData<Double>
        get() = _resultKoinValue


    init {
        _resultKoinValue.value = 0.0
    }


}