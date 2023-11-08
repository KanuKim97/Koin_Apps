package com.example.data.util

import com.example.data.remote.model.orderbook.OrderRoot
import com.example.data.remote.model.ticker.TickerData
import com.example.data.remote.model.transaction.TransactionData
import com.example.domain.entity.api.orderBook.OrderInfoEntity
import com.example.domain.entity.api.orderBook.OrderBookEntity
import com.example.domain.entity.api.ticker.TickerInfoDetailEntity
import com.example.domain.entity.api.transaction.TransactionEntity

fun toTickerDataEntityMapper(tickerData: TickerData) =
    TickerInfoDetailEntity(
        openPrice = tickerData.openPrice,
        closePrice = tickerData.closePrice,
        prevClosingPrice = tickerData.prevClosingPrice,
        minPrice = tickerData.minPrice,
        maxPrice = tickerData.maxPrice,
        tradedUnits = tickerData.tradedUnits,
        tradedUnits24H = tickerData.tradedUnits24H,
        accTradeValue = tickerData.accTradeValue,
        accTradeValue24H = tickerData.accTradeValue24H,
        fluctuatePrice = tickerData.fluctuatePrice,
        fluctuateRate = tickerData.fluctuateRate,
        date = tickerData.date
    )

fun toTransactionEntityMapper(transactionArrayList: ArrayList<TransactionData?>) =
    transactionArrayList.map { transactionData ->
        TransactionEntity(
            transactionDate = transactionData!!.transactionDate,
            type = transactionData.transactionType,
            tradedUnits = transactionData.tradedUnit,
            price = transactionData.transactionPrice,
            total = transactionData.transactionTotalPrice
        )
    }

fun toOrderBookEntityMapper(orderRoot: OrderRoot) = OrderBookEntity(
    status = orderRoot.status,
    timestamp = orderRoot.orderData?.timeStamp.toString(),
    orderCurrency = orderRoot.orderData?.orderCurrency.toString(),
    paymentCurrency = orderRoot.orderData?.orderCurrency.toString(),
    bids = orderRoot.orderData?.orderBids?.map {
        OrderInfoEntity(
            quantity = it.orderQuantity,
            price = it.orderPrice
        )
    },
    asks = orderRoot.orderData?.orderAsks?.map {
        OrderInfoEntity(
            quantity = it.orderQuantity,
            price = it.orderPrice
        )
    }
)