package com.example.data.util.mapper.api

import com.example.data.remote.model.orderbook.OrderRoot
import com.example.domain.entity.OrderAskBidEntity
import com.example.domain.entity.OrderBookEntity

fun mapperToOrderBookEntity(
    orderRoot: OrderRoot
): OrderBookEntity = OrderBookEntity(
    status = orderRoot.status,
    timestamp = orderRoot.orderData?.timeStamp.toString(),
    order_currency = orderRoot.orderData?.orderCurrency.toString(),
    payment_currency = orderRoot.orderData?.orderCurrency.toString(),
    bids = orderRoot.orderData?.orderBids?.map {
        OrderAskBidEntity(
            quantity = it.orderQuantity,
            price = it.orderPrice
        )
    },
    asks = orderRoot.orderData?.orderAsks?.map {
        OrderAskBidEntity(
            quantity = it.orderQuantity,
            price = it.orderPrice
        )
    }
)