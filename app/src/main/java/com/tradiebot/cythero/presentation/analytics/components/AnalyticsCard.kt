package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
internal fun AnalyticsCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(ANALYTICS_CARD_PADDING),
    ) {
        Text(
            text = title,
            // fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(ANALYTICS_CARD_CONTENT_PADDING)
                .padding(vertical = 16.dp),
        )
        content()
    }
}

val ANALYTICS_CARD_CONTENT_PADDING = PaddingValues(horizontal = 16.dp, vertical = 6.dp)
private val ANALYTICS_CARD_PADDING = PaddingValues(vertical = 12.dp)
