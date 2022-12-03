package com.tradiebot.cythero.presentation.analytics.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AnalyticsCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
    ) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(ANALYTICS_CARD_PADDING),
    ) {

        Column(modifier = Modifier
            .padding(vertical = 6.dp, horizontal = 16.dp)
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
}

/**
 * creates a key value pair where the key is located at the left and the value at right
 *
 * the key has : added to the end
 *
 * @param key the value on the left, : is added to the end
 * @param value the value on the right
 */
// @Preview
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

val ANALYTICS_CARD_CONTENT_PADDING = PaddingValues(vertical = 6.dp)
private val ANALYTICS_CARD_PADDING = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
