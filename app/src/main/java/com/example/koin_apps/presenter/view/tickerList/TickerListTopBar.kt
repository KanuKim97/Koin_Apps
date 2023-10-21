package com.example.koin_apps.presenter.view.tickerList

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickerListPageTopBar(
    modifier: Modifier,
    onAddClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "암호화폐 리스트",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
        },
        actions = {
            IconButton(
                onClick = onAddClick,
                modifier = modifier.wrapContentSize(),
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add more CryptoCurrency"
                    )
                }
            )
        }
    )
}