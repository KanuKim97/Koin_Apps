package com.koin.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.koin.designsystem.components.KoinTopAppBar
import com.koin.designsystem.icons.KoinIcons
import com.koin.designsystem.theme.KoinTypography
import com.koin.ui.preview.ComponentPreview

@Composable
fun HomeTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onAddActionClick: () -> Unit,
    onOptActionClick: () -> Unit
) {
    KoinTopAppBar(
        title = title,
        modifier = modifier,
        actions = {
            IconButton(
                onClick = onAddActionClick,
                content = {
                    Icon(
                        imageVector = KoinIcons.ADD_OUTLINED,
                        contentDescription = "ADD ICONS"
                    )
                }
            )
            IconButton(
                onClick = onOptActionClick,
                content = {
                    Icon(
                        imageVector = KoinIcons.OPTION_OUTLINED,
                        contentDescription = "ADD ICONS"
                    )
                }
            )
        }
    )
}

@ComponentPreview
@Composable
fun PreviewHomeTopAppBar() {
    HomeTopAppBar(
        title = {
            Text(
                text = "CryptoCurrency List",
                style = KoinTypography.titleLarge
            )
        },
        onAddActionClick = { /* TODO */ },
        onOptActionClick = { /* TODO */ }
    )
}