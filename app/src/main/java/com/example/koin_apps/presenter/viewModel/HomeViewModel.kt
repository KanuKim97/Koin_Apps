package com.example.koin_apps.presenter.viewModel

import androidx.lifecycle.*
import com.example.domain.entity.db.TickerEntity
import com.example.domain.usecase.databaseUseCase.ReadAllTickerUseCase
import com.example.koin_apps.di.qualifier.IoDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val readAllTickerUseCase: ReadAllTickerUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): ViewModel() {
    private val _tickerList = MutableStateFlow<List<TickerEntity>>(listOf())
    val tickerList: StateFlow<List<TickerEntity>> get() = _tickerList.asStateFlow()

    fun fetchAllTickerData(): Job = viewModelScope.launch(ioDispatcher) {
        readAllTickerUseCase().collect { result -> _tickerList.value = result }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}