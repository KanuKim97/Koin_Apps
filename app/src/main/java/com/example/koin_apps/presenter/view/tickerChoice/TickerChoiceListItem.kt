package com.example.koin_apps.presenter.view.tickerChoice

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TickerChoiceListItem(
    modifier: Modifier,
    ticker: String
) {
    var isChecked by rememberSaveable { mutableStateOf(false) }

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
                verticalAlignment = Alignment.CenterVertically,
                content = {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { isChecked = !isChecked }
                    )
                    Spacer(modifier = modifier.size(5.dp))
                    Text(text = ticker, fontSize = 50.sp)
                }
            )
        }
    )
}