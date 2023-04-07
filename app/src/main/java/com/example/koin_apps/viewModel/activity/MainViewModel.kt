package com.example.koin_apps.viewModel.activity

import androidx.lifecycle.*
import com.example.koin_apps.data.database.tables.TickerEntity
import com.example.koin_apps.data.di.coroutineDispatcher.IoDispatcher
import com.example.koin_apps.data.di.repository.TickerDBRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val tickerDBRepo: TickerDBRepository
): ViewModel() {
    private val _readAllTicker = MutableLiveData<List<TickerEntity>>()
    val readAllTicker: LiveData<List<TickerEntity>> get() = _readAllTicker

    fun fetchDBData(): Job = viewModelScope.launch(ioDispatcher) {
        tickerDBRepo.readAllTicker().collect { result -> _readAllTicker.postValue(result) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}