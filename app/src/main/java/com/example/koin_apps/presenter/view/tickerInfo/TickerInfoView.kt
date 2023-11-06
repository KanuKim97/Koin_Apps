package com.example.koin_apps.presenter.view.tickerInfo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TickerInfoView(modifier: Modifier) {
    Surface(
        modifier = modifier.fillMaxSize(),
        content = {
            Column(modifier = modifier.fillMaxSize()) {  }
            LazyColumn(modifier = modifier.fillMaxSize(), content = {})
            Column(modifier = modifier.fillMaxSize()) {  }
        }
    )
}


@Preview(group = "Views", showBackground = true)
@Composable
fun PreviewViews() {
    TickerInfoView(modifier = Modifier)
}
