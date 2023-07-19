package com.example.data.util.mapper.api

import com.example.data.remote.model.transaction.TransactionData
import com.example.domain.entity.TransactionEntity

fun mapperToTransactionEntity(
    transactionArrayList: ArrayList<TransactionData>
): List<TransactionEntity> = transactionArrayList.map {
    TransactionEntity(
        transaction_date = it.transactionDate,
        type = it.transactionType,
        units_traded = it.tradedUnit,
        price = it.transactionPrice,
        total = it.transactionTotalPrice
    )
}

