package com.example.koin_apps.presenter.viewModel

import androidx.lifecycle.*
import com.example.domain.entity.db.TickerEntity
import com.example.domain.usecase.databaseUseCase.ReadAllTickerUseCase
import com.example.koin_apps.module.coroutineDispatcher.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val readAllTickerUseCase: ReadAllTickerUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerAllData = MutableLiveData<List<TickerEntity>>()
    val tickerAllData: LiveData<List<TickerEntity>> get() = _tickerAllData

    fun fetchAllTickerData(): Job = viewModelScope.launch(ioDispatcher) {
        readAllTickerUseCase().collect { result -> _tickerAllData.postValue(result) }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}