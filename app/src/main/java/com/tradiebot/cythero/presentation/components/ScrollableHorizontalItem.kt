package com.tradiebot.cythero.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Single horizontally scrollable tab row
 *
 * @param modifier modifier that will be applied to the row
 * @param content the tab row content
 */
@Composable
fun ScrollableHorizontalItem(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ScrollableTabRow(
        edgePadding = 0.dp,
        indicator = { },
        modifier = modifier,
        selectedTabIndex = 0,
        divider = { },
    ) {
        Column {
            content()
        }
    }
}
