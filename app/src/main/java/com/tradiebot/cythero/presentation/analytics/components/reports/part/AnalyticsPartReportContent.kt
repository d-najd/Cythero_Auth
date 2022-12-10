package com.tradiebot.cythero.presentation.analytics.components.reports.part

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsPartScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard

@Composable
fun AnalyticsPartReportContent(
    state: AnalyticsPartScreenState.Success,
    contentPadding: PaddingValues,
) {
    Surface(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        CytheroCard(
            title = "Grades Breakdown",
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {

        }
    }
}
