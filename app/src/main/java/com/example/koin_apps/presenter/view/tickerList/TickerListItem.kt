package com.example.koin_apps.presenter.view.tickerList

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TickerListItem(modifier: Modifier) {
    Card(
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(10.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp),
        content = {
            Row(
                modifier = modifier.padding(10.dp),
                content = { Text(text = "Koin", fontSize = 50.sp) }
            )
        }
    )
}

@Preview(group = "Item", showBackground = true)
@Composable
fun PreviewTickerListItem() {
    TickerListItem(modifier = Modifier)
}
