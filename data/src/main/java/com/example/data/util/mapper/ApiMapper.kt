package com.example.data.util.mapper

import com.example.data.remote.model.orderbook.OrderRoot
import com.example.data.remote.model.ticker.TickerData
import com.example.data.remote.model.transaction.TransactionData
import com.example.domain.entity.api.orderBook.OrderInfoEntity
import com.example.domain.entity.api.orderBook.OrderBookEntity
import com.example.domain.entity.api.ticker.TickerDataEntity
import com.example.domain.entity.api.transaction.TransactionEntity

fun toTickerDataEntityMapper(
    tickerData: TickerData
): TickerDataEntity = TickerDataEntity(
    openPrice = tickerData.openPrice,
    closePrice = tickerData.closePrice,
    minPrice = tickerData.minPrice,
    maxPrice = tickerData.maxPrice,
    tradedUnits = tickerData.tradedUnits,
    accTradeValue = tickerData.accTradeValue,
    prevClosingPrice = tickerData.prevClosingPrice,
    tradedUnits_24H = tickerData.tradedUnits_24H,
    accTradeValue_24H = tickerData.accTradeValue_24H,
    fluctuatePrice = tickerData.fluctuatePrice,
    fluctuateRate = tickerData.fluctuateRate,
    date = tickerData.date
)

fun toTransactionEntityMapper(
    transactionArrayList: ArrayList<TransactionData?>
): List<TransactionEntity> = transactionArrayList.map {
    TransactionEntity(
        transaction_date = it!!.transactionDate,
        type = it.transactionType,
        units_traded = it.tradedUnit,
        price = it.transactionPrice,
        total = it.transactionTotalPrice
    )
}

fun toOrderBookEntityMapper(
    orderRoot: OrderRoot
): OrderBookEntity = OrderBookEntity(
    status = orderRoot.status,
    timestamp = orderRoot.orderData?.timeStamp.toString(),
    order_currency = orderRoot.orderData?.orderCurrency.toString(),
    payment_currency = orderRoot.orderData?.orderCurrency.toString(),
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