package com.example.domain.usecase.apiUseCase

import com.example.domain.repository.BithumbApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import javax.inject.Inject

class GetTickerAllUseCase @Inject constructor(
    private val apiRepo: BithumbApiRepository
) {
    operator fun invoke(): Flow<MutableList<String>?> =
        apiRepo.getTickerInfoAll().filter { it?.remove("date") ?: false }
}