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
import com.tradiebot.cythero.app.util.view.ContextHolder
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.domain.analytics.user.model.AnalyticsUser
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.ScrollableHorizontalItem
import com.tradiebot.cythero.presentation.components.chart.LineChart
import com.tradiebot.cythero.presentation.components.chart.LineChartHelper
import com.tradiebot.cythero.presentation.util.chart.ChartSettingsHolder
import com.tradiebot.cythero.presentation.util.chart.ChartValueFormatterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get
import kotlin.math.roundToInt

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
                generateDataSet(state.analytics[state.auth.user.id]!!))
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

private fun generateDataSet(
    analyticsUser: AnalyticsUser
): List<LineDataSet> {
    val analyticsTable = analyticsUser.analyticsUserTable
    
    val parts = analyticsTable.part.takeLast(10).map { Injekt.get<ContextHolder>().getString(it.nameId) }
    
    val lowCoverage = analyticsTable.clearLowCoverage.takeLast(10)
        .zip( analyticsTable.baseLowCoverage.takeLast(10)) { f, s -> f + s  }
        .zip( analyticsTable.primerLowCoverage.takeLast(10)) { f, s -> (f + s)/3 }
        .map { o -> o.roundToInt().toFloat() }
        .zip(parts)
    
    val goodCoverage = analyticsTable.clearGoodCoverage.takeLast(10)
        .zip( analyticsTable.baseGoodCoverage.takeLast(10)) { f, s -> f + s  }
        .zip( analyticsTable.primerGoodCoverage.takeLast(10)) { f, s -> (f + s)/3 }
        .map { o -> o.roundToInt().toFloat() }
        .zip(parts)
    
    val highCoverage = analyticsTable.clearHighCoverage.takeLast(10)
        .zip( analyticsTable.baseHighCoverage.takeLast(10)) { f, s -> f + s  }
        .zip( analyticsTable.primerHighCoverage.takeLast(10)) { f, s -> (f + s)/3 }
        .map { o -> o.roundToInt().toFloat() }
        .zip(parts)
    
    val lowCoverageDataSet = LineChartHelper.generateDataSet(
        data = lowCoverage,
        label = Injekt.get<ContextHolder>().getString(R.string.field_coverage_low),
        color = Grade.B.rgb,
    )
    
    val goodCoverageDataSet = LineChartHelper.generateDataSet(
        data = goodCoverage,
        label = Injekt.get<ContextHolder>().getString(R.string.field_coverage_good),
        color = Grade.A.rgb,
    )
    
    val highCoverageDataSet  = LineChartHelper.generateDataSet(
        data = highCoverage,
        label = Injekt.get<ContextHolder>().getString(R.string.field_coverage_high),
        color = Grade.C.rgb,
    )
    
    return listOf(lowCoverageDataSet, goodCoverageDataSet, highCoverageDataSet)
}