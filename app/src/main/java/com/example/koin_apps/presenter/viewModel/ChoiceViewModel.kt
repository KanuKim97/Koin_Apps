package com.example.koin_apps.presenter.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.db.TickerEntity
import com.example.domain.usecase.apiUseCase.GetTickerAllUseCase
import com.example.domain.usecase.databaseUseCase.InsertTickerUseCase
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
class ChoiceViewModel @Inject constructor(
    private val getTickerAllUseCase: GetTickerAllUseCase,
    private val insertTickerUseCase: InsertTickerUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
): ViewModel() {
    private val _tickerList = MutableStateFlow<List<String>>(listOf())
    private val _isSaveSuccess = MutableStateFlow(false)

    val tickerList: StateFlow<List<String>> get() = _tickerList.asStateFlow()
    val isSaveSuccess: StateFlow<Boolean> get() = _isSaveSuccess.asStateFlow()

    init { loadTickerTitle() }

    private fun loadTickerTitle(): Job = viewModelScope.launch(ioDispatcher) {
        getTickerAllUseCase().collect {
            if (it != null) {
                _tickerList.value = it
            } else {
                _tickerList.value = listOf()
            }
        }
    }

    fun storeTickerTitle(tickerList: List<String>): Job = viewModelScope.launch(ioDispatcher) {
        tickerList.forEach {
            insertTickerUseCase(TickerEntity(it)).collect { result ->
                _isSaveSuccess.value = result.isSuccess
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}