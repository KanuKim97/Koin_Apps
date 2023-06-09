package com.example.data.util.apiMapper


import com.example.data.remote.model.orderbook.OrderData
import com.example.domain.entity.OrderAskBidEntity
import com.example.domain.entity.OrderBookEntity

fun mapperToOrderBookEntity(
    orderBookData: OrderData
): OrderBookEntity = OrderBookEntity(
    status = orderBookData.status,
    timestamp = orderBookData.timestamp,
    order_currency = orderBookData.order_currency,
    payment_currency = orderBookData.payment_currency,
    bids = orderBookData.bids?.map { OrderAskBidEntity(quantity = it.quantity, price = it.price) },
    asks = orderBookData.asks?.map { OrderAskBidEntity(quantity = it.quantity, price = it.price) }
)