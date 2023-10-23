package com.example.koin_apps.presenter.view.tickerChoice

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TickerChoiceBtn(
    modifier: Modifier,
    onChoiceComplete: () -> Unit
) {
    Button(
        onClick = onChoiceComplete,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = ShapeDefaults.Large,
        elevation = ButtonDefaults.elevatedButtonElevation(10.dp),
        content = { Text(text = "선택완료", fontSize = 20.sp, fontWeight = FontWeight.Bold) }
    )
}

@Composable
fun TickerChoiceBottomBar(
    modifier: Modifier,
    onChoiceComplete: () -> Unit
) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        content = { TickerChoiceBtn(modifier = modifier, onChoiceComplete = onChoiceComplete) }
    )
}