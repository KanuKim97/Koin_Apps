package com.example.koin_apps.composeView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.koin_apps.composeView.theme.Blue100
import com.example.koin_apps.composeView.theme.Mint100

@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .background(color = Mint100)
        .padding(10.dp)
        .fillMaxSize()
    ) {
        TitleSection()
    }
}

@Composable
fun TitleSection() {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(
                text = "coinList",
                style = MaterialTheme.typography.titleMedium,
                color = Blue100
            )
        }
    }
}

@Preview
@Composable
fun PreviewCompose() {
    HomeScreen()
}