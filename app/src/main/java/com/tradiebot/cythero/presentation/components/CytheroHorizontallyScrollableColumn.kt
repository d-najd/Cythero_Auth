package com.tradiebot.cythero.presentation.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Single horizontally scrollable tab row
 *
 * @param modifier modifier that will be applied to the row
 * @param content the tab row content
 */
@Composable
fun CytheroHorizontallyScrollableColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .horizontalScroll(rememberScrollState()),
    ) {
        content()
    }
}
