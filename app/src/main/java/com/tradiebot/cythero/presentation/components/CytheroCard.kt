package com.tradiebot.cythero.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField

@Composable
fun CytheroCard(
    title: String? = null,
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
            if(title != null) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(ANALYTICS_CARD_CONTENT_PADDING)
                        .padding(vertical = 16.dp),
                )
            }
            content()
        }
    }
}

@Preview(
    widthDp = 300,
)
@Composable
private fun AnalyticsCardPreview(){
    CytheroCard(title = "title") {
        AnalyticsPairField(
            key = "key1",
            value = "value1"
        )
        AnalyticsPairField(
            key = "key2",
            value = "value2"
        )
        CytheroMultipurposeMenu(
            title = "Top Title",
            text = "Selected Item",
        ) {
            DropdownMenuItem(text = { Text(text = "Dropdown Item 1") }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Dropdown Item 2") }, onClick = { /*TODO*/ })
            DropdownMenuItem(text = { Text(text = "Dropdown Item 3") }, onClick = { /*TODO*/ })
        }
    }
}

val ANALYTICS_CARD_CONTENT_PADDING = PaddingValues(vertical = 6.dp)
private val ANALYTICS_CARD_PADDING = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
