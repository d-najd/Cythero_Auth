package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * creates a key value pair where the key is located at the left and the value at right
 *
 * the key has : added to the end
 *
 * @param key the value on the left, : is added to the end
 * @param value the value on the right
 */
@Composable
fun AnalyticsPairField(
    key: String,
    value: String,
){
    val cardContentPadding = ANALYTICS_CARD_CONTENT_PADDING

    Row(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            text = "$key:",
            modifier = Modifier
                .padding(cardContentPadding),
        )
        Text(
            text = value,
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(cardContentPadding)
                .padding(top = 2.dp) // the text on the left is a bit lower
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun AnalyticsPartFieldPreview(
){
    val key = "key"
    val value = "value"
    AnalyticsPairField(
        key = key,
        value = value
    )
}
