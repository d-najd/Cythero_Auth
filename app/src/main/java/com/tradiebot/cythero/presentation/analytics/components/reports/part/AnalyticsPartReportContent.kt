package com.tradiebot.cythero.presentation.analytics.components.reports.part

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.presentation.analytics.components.reports.part.cards.AnalyticsPartCoverageBreakdownCard
import com.tradiebot.cythero.presentation.analytics.components.reports.part.cards.AnalyticsPartGradesBreakdownCard
import com.tradiebot.cythero.presentation.analytics.components.reports.part.cards.AnalyticsPartPaintUsed
import com.tradiebot.cythero.presentation.analytics.components.reports.part.cards.AnalyticsTimeTakenCard

@Composable
fun AnalyticsPartReportContent(
    state: AnalyticsScreenState.PartSuccess,
    onUpdatePartSelectedCoverageType: (CoverageType) -> Unit,
) {
    AnalyticsPartGradesBreakdownCard(
        state = state,
        onUpdatePartSelectedCoverageType = onUpdatePartSelectedCoverageType
    )

    AnalyticsTimeTakenCard(
        state = state,
    )

    Divider(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
    )

    AnalyticsPartCoverageBreakdownCard(
        state = state,
    )

    AnalyticsPartPaintUsed(
        state = state,
    )

    Divider(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
    )
}
