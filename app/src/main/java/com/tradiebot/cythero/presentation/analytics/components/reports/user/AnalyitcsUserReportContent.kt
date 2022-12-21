package com.tradiebot.cythero.presentation.analytics.components.reports.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.AnalyticsCoverageGraph
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.AnalyticsGeneralCard
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.AnalyticsGradeCard
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.AnalyticsLatestSessionCard

@Composable
fun AnalyticsUserReportContent(
    state: AnalyticsScreenState.UserSuccess,
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
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
    )
}