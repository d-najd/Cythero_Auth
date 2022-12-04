package com.tradiebot.cythero.presentation.components

import androidx.compose.material3.ScrollableTabRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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
        selectedTabIndex = 0,
        modifier = modifier,
        indicator = { },
        divider = { },
    ) {
        content()
    }
}
