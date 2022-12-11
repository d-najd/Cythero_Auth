package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendOrientation
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.LineDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsPartScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem
import com.tradiebot.cythero.presentation.components.charts.LineChart
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper
import com.tradiebot.cythero.util.CytheroDateFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Composable
fun AnalyticsTimeTakenCard(
    state: AnalyticsPartScreenState.Success,
    selectedCoverageType: CoverageType,
){
    val analytics = state.analytics[0]

    ScrollableHorizontalItem {
        CytheroCard(
            modifier = Modifier
                .width(750.dp),
            title = stringResource(R.string.field_time_taken_last_sessions),
        ) {
            Column(
                modifier = Modifier
                    .height(350.dp)
                    .fillMaxSize()
            ) {
                val dates = analytics.sessionEnd.takeLast(10)
                    .map { o -> CytheroDateFormat.defaultChartDateFormat().format(o) }

                val overallTimeData =
                    LineChartHelper.generatePartTimeTakenData(analytics.overallTime, dates)
                val primerTimeData =
                    LineChartHelper.generatePartTimeTakenData(analytics.primerTime, dates)
                val baseTimeData =
                    LineChartHelper.generatePartTimeTakenData(analytics.baseTime, dates)
                val clearTimeData =
                    LineChartHelper.generatePartTimeTakenData(analytics.clearTime, dates)

                val dataSet: Flow<List<LineDataSet>> = flow {
                    when (selectedCoverageType) {
                        CoverageType.OVERALL -> emit(overallTimeData)
                        CoverageType.PRIMER -> emit(primerTimeData)
                        CoverageType.BASE -> emit(baseTimeData)
                        CoverageType.CLEAR -> emit(clearTimeData)
                    }
                }
                LineChart(
                    dataSets = dataSet,
                    xAxisPosition = XAxisPosition.BOTTOM,
                    offsets = Offset(0f, -10f),
                    verticalValueFormatter = LineChartHelper.LineValueFormatterType.VALUE,
                )
            }
            Column(
                modifier = Modifier
                    .padding(5.dp)
            ) { }
        }
    }
}