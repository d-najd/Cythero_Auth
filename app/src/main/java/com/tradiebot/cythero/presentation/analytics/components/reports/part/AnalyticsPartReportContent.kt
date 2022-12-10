package com.tradiebot.cythero.presentation.analytics.components.reports.part

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsPartScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.presentation.analytics.components.reports.part.cards.AnalyticsPartGradesBreakdownCard
import com.tradiebot.cythero.presentation.analytics.components.reports.part.cards.AnalyticsTimeTakenCard

@Composable
fun AnalyticsPartReportContent(
    state: AnalyticsPartScreenState.Success,
    contentPadding: PaddingValues,
) {
    var selectedCoverageType by remember { mutableStateOf(CoverageType.OVERALL) }

    AnalyticsPartGradesBreakdownCard(
        state = state,
        selectedCoverageType = selectedCoverageType,
        onSelectedCoverageTypeChange = { selectedCoverageType = it }
    )

    AnalyticsTimeTakenCard(
        state = state,
        selectedCoverageType = selectedCoverageType
    )

    Divider(
        modifier = Modifier.padding(horizontal = 4.dp, vertical = 16.dp)
    )
}
