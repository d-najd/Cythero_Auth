package com.tradiebot.cythero.presentation.analytics.components.reports.user.cards

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.LineDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem
import com.tradiebot.cythero.presentation.components.chart.LineChart
import com.tradiebot.cythero.presentation.components.chart.LineChartHelper
import com.tradiebot.cythero.presentation.util.chart.ChartSettingsHolder
import com.tradiebot.cythero.presentation.util.chart.ChartValueFormatterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun AnalyticsCoverageGraph(
    state: AnalyticsScreenState.UserSuccess,
) {
    ScrollableHorizontalItem {
        CytheroCard(
            modifier = Modifier
                .height(400.dp)
                .width(600.dp),
            title = stringResource(R.string.field_coverage_graph_last_sessions),
        ) {
            val dataSets: Flow<List<LineDataSet>> = flow { emit(
                LineChartHelper.generateUserPartsData(state.analytics[state.auth.user.id]!!))
            }

            val chartSettingsHolder = ChartSettingsHolder.defaultBarLineCSettings()
            chartSettingsHolder.xAxisValueFormatter = ChartValueFormatterType.VALUE_POSITION

            LineChart(
                dataSets = dataSets,
                chartSettingsHolder = chartSettingsHolder,
            )
        }
    }
}
