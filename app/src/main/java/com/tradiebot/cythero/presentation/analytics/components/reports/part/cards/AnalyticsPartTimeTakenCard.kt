package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.data.LineDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.app.util.view.ContextHolder
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.CytheroHorizontallyScrollableColumn
import com.tradiebot.cythero.presentation.components.chart.LineChart
import com.tradiebot.cythero.presentation.components.chart.LineChartHelper
import com.tradiebot.cythero.presentation.util.chart.ChartSettingsHolder
import com.tradiebot.cythero.presentation.util.chart.ChartValueFormatterType
import com.tradiebot.cythero.util.CytheroDateFormat
import kotlinx.coroutines.flow.flow
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

@Composable
fun AnalyticsTimeTakenCard(
    state: AnalyticsScreenState.PartSuccess,
    selectedCoverageType: CoverageType,
){
    val analytics = state.analytics[0]
    
    CytheroHorizontallyScrollableColumn {
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
                val partTimeTakenDataSet = generateDataSet(
                    analytics = analytics,
                    coverageType = selectedCoverageType
                )
                
                val chartSettingsHolder = ChartSettingsHolder.defaultBarLineCSettings()
                chartSettingsHolder.xAxis.position = XAxisPosition.BOTTOM
                chartSettingsHolder.xAxisValueFormatter = ChartValueFormatterType.VALUE
                chartSettingsHolder.bottomOffset = 10f
                
                LineChart(
                    dataSets = flow { emit(partTimeTakenDataSet) },
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
    analytics: AnalyticsPart,
    coverageType: CoverageType,
): List<LineDataSet> {
    val dates = analytics.sessionEnd.takeLast(10)
        .map { o -> CytheroDateFormat.defaultChartDateFormat().format(o) }
    
    val overallTimeData = analytics.overallTime.takeLast(10)
        .map { o -> o/60f }
        .zip(dates)
    
    val primerTimeData = analytics.primerTime.takeLast(10)
        .map { o -> o/60f }
        .zip(dates)
    
    val baseTimeData = analytics.baseTime.takeLast(10)
        .map { o -> o/60f }
        .zip(dates)
    
    val clearTimeData = analytics.clearTime.takeLast(10)
        .map { o -> o/60f }
        .zip(dates)
    
    return when (coverageType){
        CoverageType.OVERALL -> formDataSet(overallTimeData)
        CoverageType.PRIMER -> formDataSet(primerTimeData)
        CoverageType.BASE -> formDataSet(baseTimeData)
        CoverageType.CLEAR -> formDataSet(clearTimeData)
    }
}


private fun formDataSet(
    data: List<Pair<Float, String>>
): List<LineDataSet> {
    return listOf(
        LineChartHelper.generateDataSet(
            data = data,
            label = Injekt.get<ContextHolder>().getString(R.string.field_sessions),
            color = Grade.A.rgb
        )
    )
}