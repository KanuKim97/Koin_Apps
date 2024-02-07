package com.koin.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.koin.designsystem.components.KoinCard
import com.koin.designsystem.theme.KoinShape
import com.koin.designsystem.theme.KoinTypography
import com.koin.ui.preview.ComponentPreview

@Composable
fun TickerChoiceItem(
    ticker: String,
    checkedItemList: MutableList<String>,
    modifier: Modifier = Modifier
) {
    var isItemChecked by rememberSaveable { mutableStateOf(false) }

    KoinCard(
        modifier = modifier,
        shape = KoinShape.extraSmall,
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                content = {
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
                    Text(
                        text = ticker,
                        style = KoinTypography.displayMedium
                    )
                }
            )
        }
    )
}

@ComponentPreview
@Composable
fun PreviewChoiceItem() {
    TickerChoiceItem(
        ticker = "BTC",
        checkedItemList = mutableListOf(),
        modifier = Modifier.padding(10.dp).fillMaxWidth().wrapContentHeight()
    )
}