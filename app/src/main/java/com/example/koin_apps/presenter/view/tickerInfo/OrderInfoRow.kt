package com.example.koin_apps.presenter.view.tickerInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun OrderInfoRow(modifier: Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(text = "매도 수량", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(text = "매도", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(text = "가격", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(text = "매수", fontSize = 13.sp, fontWeight = FontWeight.Bold)
            Text(text = "매수 수량", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        }
    )
}
