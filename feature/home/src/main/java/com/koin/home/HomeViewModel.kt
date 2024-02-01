package com.koin.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koin.database.model.KoinTable
import com.koin.domain.ReadAllTickerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    readAllTickerUseCase: ReadAllTickerUseCase
): ViewModel() {
    val readTickerState: StateFlow<ReadTickerUiState> =
        readTickerState(readAllTickerUseCase)
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = ReadTickerUiState.IsLoading
            )

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}

private fun readTickerState(
    readAllTickerUseCase: ReadAllTickerUseCase
): Flow<ReadTickerUiState> = readAllTickerUseCase()
    .onStart { ReadTickerUiState.IsLoading }
    .catch { exception ->
        ReadTickerUiState.IsFailed(exception.message)
    }
    .map <List<KoinTable>, ReadTickerUiState> { result ->
        ReadTickerUiState.IsSuccess(result)
    }

sealed interface ReadTickerUiState {
    data object IsLoading: ReadTickerUiState

    data class IsSuccess(val data: List<KoinTable>): ReadTickerUiState

    data class IsFailed(val exception: String?): ReadTickerUiState
}