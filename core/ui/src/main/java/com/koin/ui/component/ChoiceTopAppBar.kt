package com.koin.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.koin.designsystem.components.KoinTopAppBar
import com.koin.designsystem.icons.KoinIcons
import com.koin.ui.preview.ComponentPreview

@Composable
fun ChoiceTopAppBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit
) {
    KoinTopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = {
            IconButton(
                onClick = onNavigationIconClick,
                content = {
                    Icon(
                        imageVector = KoinIcons.ARROW_BACK_OUTLINED,
                        contentDescription = "Arrow Back"
                    )
                }
            )
        }
    )
}

@ComponentPreview
@Composable
fun PreviewChoiceTopAppBar() {
    ChoiceTopAppBar(
        title = { /*TODO*/ },
        onNavigationIconClick = { /*TODO*/ }
    )
}