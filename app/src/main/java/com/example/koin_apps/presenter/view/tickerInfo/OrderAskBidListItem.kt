package com.example.koin_apps.presenter.view.tickerInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrderAskListItem(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            OrderTransactionPriceInfo(modifier = modifier, transactionPrice = "10,000원")
            OrderTransactionAmountInfo(modifier = modifier, transactionAmount = "1,000")
        }
    )
}

@Composable
fun OrderBidListItem(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            OrderTransactionAmountInfo(modifier = modifier, transactionAmount = "1,000")
            OrderTransactionPriceInfo(modifier = modifier, transactionPrice = "10,000원")
        }
    )
}

@Composable
fun OrderTransactionAmountInfo(
    modifier: Modifier,
    transactionAmount: String
) {
    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(text = "거래량:")
            Spacer(modifier = modifier.size(3.dp))
            Text(text = transactionAmount)
        }
    )
}

@Composable
fun OrderTransactionPriceInfo(
    modifier: Modifier,
    transactionPrice: String
) {
    Row(
        modifier = modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(text = "거래가:")
            Spacer(modifier = modifier.size(3.dp))
            Text(text = transactionPrice)
        }
    )
}