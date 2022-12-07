package com.tradiebot.cythero.presentation.analytics.components.reports.user

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsBasicFields
import com.tradiebot.cythero.presentation.analytics.components.reports.user.cards.*

@Composable
fun AnalyticsUserReportContent(
    state: AnalyticsScreenState.Success,
    scrollState: ScrollState,
    contentPadding: PaddingValues,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(contentPadding)
            //.padding(vertical = 24.dp)
            .verticalScroll(scrollState)
            .fillMaxSize(),
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
}