package com.koin.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koin.designsystem.components.KoinCard
import com.koin.designsystem.theme.KoinShape
import com.koin.designsystem.theme.KoinTypography

@Composable
fun TickerHomeItem(
    ticker: String,
    onCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    KoinCard(
        onClick = onCardClick,
        modifier = modifier,
        shape = KoinShape.extraSmall,
        content = {
            Text(
                text = ticker,
                modifier = modifier.padding(10.dp),
                style = KoinTypography.displayMedium
            )
        }
    )
}