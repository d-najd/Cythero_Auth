package com.tradiebot.cythero.presentation.analytics.components.reports.user

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserReportScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsBasicFields
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.*

@Composable
fun AnalyticsUserReportContent(
    state: AnalyticsUserReportScreenState.Success,
    contentPadding: PaddingValues,
) {
    AnalyticsGeneralCard(
        state = state,
    )

    AnalyticsGradeCard(
        state = state,
    )

    AnalyticsLatestSessionCard(
        state = state,
    )

    AnalyticsCoverageGraph(
        state = state
    )

    Divider(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 12.dp)
    )

    AnalyticsBasicFields(
        state = state,
    )

}