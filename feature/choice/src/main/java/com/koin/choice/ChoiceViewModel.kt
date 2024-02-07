package com.koin.choice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koin.database.model.KoinTable
import com.koin.domain.GetTickerAllUseCase
import com.koin.domain.InsertTickerUseCase
import com.koin.model.ticker.TickerAllRoot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChoiceViewModel @Inject constructor(
    private val getTickerAllUseCase: GetTickerAllUseCase,
    private val insertTickerUseCase: InsertTickerUseCase,

): ViewModel() {
    private val _tickerAllUiState = MutableStateFlow<TickerAllUiState>(TickerAllUiState.IsLoading)
    private val _insertSuccessUiState = MutableStateFlow<InsertTickerUiState>(InsertTickerUiState.IsLoading)

    val tickerAllUiState: StateFlow<TickerAllUiState> = _tickerAllUiState
    val insertSuccessUiState: StateFlow<InsertTickerUiState> = _insertSuccessUiState

    fun getTickerAll(currency: String) = viewModelScope.launch {
        getTickerAllState(currency, getTickerAllUseCase).collect { tickerState ->
            _tickerAllUiState.value = tickerState
        }
    }

    fun insertTicker(tickerList: List<String>) = viewModelScope.launch {
        tickerList.forEach { ticker ->
            insertTickerUiState(ticker, insertTickerUseCase).collect { insertState ->
                _insertSuccessUiState.value = insertState
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

private fun getTickerAllState(
    currency: String,
    getTickerAllUseCase: GetTickerAllUseCase
): Flow<TickerAllUiState> = getTickerAllUseCase(currency = currency)
    .onStart { TickerAllUiState.IsLoading }
    .catch { exception ->
        TickerAllUiState.IsFailed(exception.message)
    }.map<TickerAllRoot, TickerAllUiState> { result ->
        TickerAllUiState.IsSuccess(result)
    }

private fun insertTickerUiState(
    ticker: String,
    insertTickerUseCase: InsertTickerUseCase
): Flow<InsertTickerUiState> {
    val koinEntity = KoinTable(ticker)

    return insertTickerUseCase(koinEntity)
        .onStart { InsertTickerUiState.IsLoading }
        .catch { exception ->
            InsertTickerUiState.IsFailed(exception.message)
        }.map<Result<Unit>, InsertTickerUiState> { result ->
            InsertTickerUiState.IsSuccess(result)
        }
}

sealed interface TickerAllUiState {
    data object IsLoading: TickerAllUiState

    data class IsSuccess(val data: TickerAllRoot): TickerAllUiState

    data class IsFailed(val data: String?): TickerAllUiState
}

sealed interface InsertTickerUiState {
    data object IsLoading: InsertTickerUiState

    data class IsSuccess(val data: Result<Unit>): InsertTickerUiState

    data class IsFailed(val data: String?): InsertTickerUiState
}