package com.tradiebot.cythero.presentation.analytics.components.subcards

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem
import com.tradiebot.cythero.presentation.components.charts.LineChart
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper

@Composable
fun AnalyticsCoverageGraph(
    state: AnalyticsScreenState.Success,
) {
    ScrollableHorizontalItem {
        AnalyticsCard(
            modifier = Modifier
                .height(400.dp)
                .width(750.dp),
            title = stringResource(R.string.field_coverage_graph_last_sessions),
        ) {
           val dataSet = LineChartHelper.generatePartsDataSet(state.userAnalytics[state.auth.user.id]!!)

            LineChart(
                dataSet = dataSet,
            )
        }
    }
}
