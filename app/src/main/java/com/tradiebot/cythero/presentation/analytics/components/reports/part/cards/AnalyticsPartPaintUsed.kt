package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.LineDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem
import com.tradiebot.cythero.presentation.components.charts.LineChart
import com.tradiebot.cythero.presentation.components.charts.LineChartHelper
import com.tradiebot.cythero.presentation.util.ChartsHelper
import com.tradiebot.cythero.util.CytheroDateFormat
import com.tradiebot.cythero.util.mAppContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.round

@Composable
fun AnalyticsPartPaintUsed(
    state: AnalyticsScreenState.PartSuccess,
    selectedCoverageType: CoverageType,
) {
    val analytics = state.analytics[0]

    ScrollableHorizontalItem {
        CytheroCard(
            modifier = Modifier
                .width(750.dp),
            title = stringResource(R.string.field_paint_used_last_sessions),
        ) {
            Column(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxSize()
            ) {
                val dates = analytics.sessionEnd.takeLast(10)
                    .map { o -> CytheroDateFormat.defaultChartDateFormat().format(o) }

                val overallTimeData = LineChartHelper.generateDataSet(
                        data = analytics.overallPaintUsed.map { o -> round(o.toFloat()) }.zip(dates),
                        label = mAppContext().getString(R.string.field_sessions),
                        color = Grade.A.rgb
                    )
                val primerTimeData = LineChartHelper.generateDataSet(
                    data = analytics.primerPaintUsed.map { o -> round(o.toFloat()) }.zip(dates),
                    label = mAppContext().getString(R.string.field_sessions),
                    color = Grade.A.rgb
                )
                val baseTimeData = LineChartHelper.generateDataSet(
                    data = analytics.basePaintUsed.map { o -> round(o.toFloat()) }.zip(dates),
                    label = mAppContext().getString(R.string.field_sessions),
                    color = Grade.A.rgb
                )
                val clearTimeData = LineChartHelper.generateDataSet(
                    data = analytics.basePaintUsed.map { o -> round(o.toFloat()) }.zip(dates),
                    label = mAppContext().getString(R.string.field_sessions),
                    color = Grade.A.rgb
                )

                val dataSet: Flow<List<LineDataSet>> = flow {
                    when (selectedCoverageType) {
                        CoverageType.OVERALL -> emit(listOf(overallTimeData))
                        CoverageType.PRIMER -> emit(listOf(primerTimeData))
                        CoverageType.BASE -> emit(listOf(baseTimeData))
                        CoverageType.CLEAR -> emit(listOf(clearTimeData))
                    }
                }

                val legend = ChartsHelper.defaultLineCLegend()
                legend.xAxisPosition = XAxis.XAxisPosition.BOTTOM
                legend.verticalValueFormatter = LineChartHelper.LineValueFormatterType.VALUE

                LineChart(
                    dataSets = dataSet,
                    legend = legend,
                    offsets = Offset(0f, -10f),
                )
            }
            Column(
                modifier = Modifier
                    .padding(2.dp)
            ) { }
        }
    }
}