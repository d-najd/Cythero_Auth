package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.LineDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.app.util.view.ContextHolder
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem
import com.tradiebot.cythero.presentation.components.chart.LineChart
import com.tradiebot.cythero.presentation.components.chart.LineChartHelper
import com.tradiebot.cythero.presentation.util.chart.ChartSettingsHolder
import com.tradiebot.cythero.presentation.util.chart.ChartValueFormatterType
import com.tradiebot.cythero.util.CytheroDateFormat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

@Composable
fun AnalyticsTimeTakenCard(
    state: AnalyticsScreenState.PartSuccess,
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
                    .height(300.dp)
                    .fillMaxSize()
            ) {
                val dates = analytics.sessionEnd.takeLast(10)
                    .map { o -> CytheroDateFormat.defaultChartDateFormat().format(o) }

                val overallTimeData =
                    generateDataSet(analytics.overallTime, dates)
                val primerTimeData =
                    generateDataSet(analytics.primerTime, dates)
                val baseTimeData =
                    generateDataSet(analytics.baseTime, dates)
                val clearTimeData =
                    generateDataSet(analytics.clearTime, dates)

                val dataSet: Flow<List<LineDataSet>> = flow {
                    when (selectedCoverageType) {
                        CoverageType.OVERALL -> emit(overallTimeData)
                        CoverageType.PRIMER -> emit(primerTimeData)
                        CoverageType.BASE -> emit(baseTimeData)
                        CoverageType.CLEAR -> emit(clearTimeData)
                    }
                }

                val chartSettingsHolder = ChartSettingsHolder.defaultBarLineCSettings()
                chartSettingsHolder.xAxis.position = XAxisPosition.BOTTOM
                chartSettingsHolder.xAxisValueFormatter = ChartValueFormatterType.VALUE
                chartSettingsHolder.offsets = Offset(0f, -10f)

                LineChart(
                    dataSets = dataSet,
                    chartSettingsHolder = chartSettingsHolder,
                )
            }
            Column(
                modifier = Modifier
                    .padding(2.dp)
            ) { }
        }
    }
}

private fun generateDataSet(
    timeList: List<Int>,
    dates: List<String>,
): List<LineDataSet> {
    val formattedList = timeList.takeLast(10)
        .map { o -> o/60f }
        .zip(dates)
    
    val dataSet = LineChartHelper.generateDataSet(
        data = formattedList,
        label = Injekt.get<ContextHolder>().getString(R.string.field_sessions),
        color = Grade.A.rgb
    )
    
    return listOf(dataSet)
}