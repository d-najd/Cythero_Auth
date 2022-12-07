package com.tradiebot.cythero.presentation.analytics.components.reports.user.cards

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsReportTypeScreenState
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsUserReportScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem
import com.tradiebot.cythero.presentation.components.charts.LineChart
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper

@Composable
fun AnalyticsCoverageGraph(
    state: AnalyticsUserReportScreenState.Success,
) {
    ScrollableHorizontalItem {
        CytheroCard(
            modifier = Modifier
                .height(400.dp)
                .width(750.dp),
            title = stringResource(R.string.field_coverage_graph_last_sessions),
        ) {
           val dataSet = LineChartHelper.generatePartsDataSet(state.userAnalytics[state.auth.user.id]!!)

            LineChart(
                dataSets = dataSet,
            )
        }
    }
}
