package com.koin.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koin.designsystem.components.KoinCard
import com.koin.designsystem.theme.KoinShape
import com.koin.designsystem.theme.KoinTypography

@Composable
fun TickerChoiceItem(
    ticker: String,
    checkedItemList: MutableList<String>,
    modifier: Modifier = Modifier
) {
    var isItemChecked by rememberSaveable { mutableStateOf(false) }

    KoinCard(
        onClick = { /*TODO*/ },
        modifier = modifier,
        shape = KoinShape.extraSmall,
        content = {
            Row {
                Checkbox(
                    checked = isItemChecked,
                    onCheckedChange = {
                        isItemChecked = !isItemChecked

                        if (isItemChecked) {
                            checkedItemList.add(ticker)
                        } else {
                            checkedItemList.remove(ticker)
                        }
                    }
                )
                Spacer(modifier = modifier.size(5.dp))
                Text(
                    text = ticker,
                    style = KoinTypography.displayMedium
                )
            }
        }
    )
}