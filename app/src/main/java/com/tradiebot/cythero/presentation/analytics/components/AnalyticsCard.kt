package com.tradiebot.cythero.presentation.analytics.components

import android.content.res.Resources.Theme
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import com.tradiebot.cythero.app.theme.CytheroTheme

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
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(ANALYTICS_CARD_CONTENT_PADDING)
                    .padding(vertical = 16.dp),
            )
            content()

        }
    }
}

@Preview(
    widthDp = 300,
)
@Composable
private fun AnalyticsCardPreview(){
    AnalyticsCard(title = "title") {
        AnalyticsPairField(
            key = "key1",
            value = "value1"
        )
        AnalyticsPairField(
            key = "key2",
            value = "value2"
        )
        AnalyticsPairField(
            key = "key3",
            value = "value3"
        )
    }
}

val ANALYTICS_CARD_CONTENT_PADDING = PaddingValues(vertical = 6.dp)
private val ANALYTICS_CARD_PADDING = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
