package com.example.koin_apps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveTimeViewModel: ViewModel() {
    private val _currentKoinValue = MutableLiveData<Int?>()

    val currentKoinValue: LiveData<Int?>
        get() = _currentKoinValue

    init {
        _currentKoinValue.value = 0
    }

}