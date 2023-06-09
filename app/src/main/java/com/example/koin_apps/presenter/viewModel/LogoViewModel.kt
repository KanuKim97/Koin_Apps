package com.example.koin_apps.presenter.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TickerEntity
import com.example.domain.usecase.databaseUseCase.ReadAllTickerUseCase
import com.example.koin_apps.module.coroutineDispatcher.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogoViewModel @Inject constructor(
    private val readAllTickerUseCase: ReadAllTickerUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _readAllTicker = MutableLiveData<List<TickerEntity>>()
    val readAllTicker: LiveData<List<TickerEntity>> get() = _readAllTicker

    init { fetchTickerAllData() }

    private fun fetchTickerAllData(): Job = viewModelScope.launch(ioDispatcher) {
        readAllTickerUseCase().collect { result -> _readAllTicker.postValue(result)}
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}