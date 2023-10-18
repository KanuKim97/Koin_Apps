package com.example.koin_apps.presenter.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TickerListPage(modifier: Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TickerListPageTopBar(
                modifier = modifier,
                onAddClick = { /* TODO */ }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {

            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TickerListPageTopBar(
    modifier: Modifier,
    onAddClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "암호화폐 리스트") },
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

@Composable
fun TickerListItem(modifier: Modifier) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp),
        content = { Text(text = "Koin") }
    )
}

@Preview(group = "Item", showBackground = true)
@Composable
fun PreviewTickerListItem() {
    TickerListItem(modifier = Modifier)
}

@Preview(group = "Page", showBackground = true)
@Composable
fun PreviewTickerListPage() {
    TickerListPage(modifier = Modifier)
}