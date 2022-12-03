package com.tradiebot.cythero.presentation.components

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Single horizontal scrollable item
 */
@Composable
fun ScrollableHorizontalRow(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = 0,
        modifier = modifier,
        indicator = { },
        divider = { },
    ) {
        content()
    }
}
