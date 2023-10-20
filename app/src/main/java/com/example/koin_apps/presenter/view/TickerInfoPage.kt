package com.example.koin_apps.presenter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TickerInfoPage(modifier: Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        content = { paddingValues ->
            Column(
                modifier = modifier.padding(paddingValues),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
                content = {
                    Text(
                        text = "Ticker_Title",
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "10000원",
                        modifier = modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Row(
                        modifier = modifier.wrapContentHeight().fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        content = {
                            Text(
                                text = "10%",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = modifier.size(5.dp))
                            Text(
                                text = "10000원",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    )
                }
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInfoPage() {
    TickerInfoPage(modifier = Modifier)
}