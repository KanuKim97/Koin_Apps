package com.example.data.util.apiMapper

import com.example.data.remote.model.transaction.TransactionData
import com.example.domain.entity.TransactionEntity

fun mapperToTransactionEntity(
    transactionArrayList: ArrayList<TransactionData>
): List<TransactionEntity> = transactionArrayList.map {
    TransactionEntity(
        transaction_date = it.transaction_date,
        type = it.type,
        units_traded = it.units_traded,
        price = it.price,
        total = it.total
    )
}

