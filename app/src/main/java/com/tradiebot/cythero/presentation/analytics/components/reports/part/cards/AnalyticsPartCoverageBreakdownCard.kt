package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.screen_models.AnalyticsPartScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.charts.PieChart
import com.tradiebot.cythero.presentation.components.charts.PieChartHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.math.round

@Composable
fun AnalyticsPartCoverageBreakdownCard(
    state: AnalyticsPartScreenState.Success,
    selectedCoverageType: CoverageType,
) {
    val analytics = state.analytics[0]

    val gradePieDataSet = mDataSet(
        analytics = analytics,
        selectedCoverageType = selectedCoverageType
    )

    CytheroCard(
        title = stringResource(R.string.field_coverage_breakdown)
    ) {
        Column(
            modifier = Modifier
                .height(150.dp)
        ) {
            PieChart(dataSet = gradePieDataSet)
        }
    }
}

@Composable
private fun mDataSet(
    analytics: AnalyticsPart,
    selectedCoverageType: CoverageType,
): Flow<PieDataSet> {
    val colorsList = listOf(
        Grade.C.rgb,
        Grade.A.rgb,
        Grade.B.rgb
    )

    val coveragePrimer = PieChartHelper.dataFromEntriesAndColors(
        entries = listOf(
            PieEntry(round(analytics.averageHighCoveragePrimer).toFloat(), stringResource(R.string.field_coverage_high)),
            PieEntry(round(analytics.averageGoodCoveragePrimer).toFloat(), stringResource(R.string.field_coverage_good)),
            PieEntry(round(analytics.averageLowCoveragePrimer).toFloat(), stringResource(R.string.field_coverage_low)),
        ),
        colors = colorsList
    )
    val coverageBase = PieChartHelper.dataFromEntriesAndColors(
        entries = listOf(
            PieEntry(round(analytics.averageHighCoverageBase).toFloat(), stringResource(R.string.field_coverage_high)),
            PieEntry(round(analytics.averageGoodCoverageBase).toFloat(), stringResource(R.string.field_coverage_good)),
            PieEntry(round(analytics.averageLowCoverageBase).toFloat(), stringResource(R.string.field_coverage_low)),
        ),
        colors = colorsList
    )
    val coverageClear = PieChartHelper.dataFromEntriesAndColors(
        entries = listOf(
            PieEntry(round(analytics.averageHighCoverageClear).toFloat(), stringResource(R.string.field_coverage_high)),
            PieEntry(round(analytics.averageGoodCoverageClear).toFloat(), stringResource(R.string.field_coverage_good)),
            PieEntry(round(analytics.averageLowCoverageClear).toFloat(), stringResource(R.string.field_coverage_low)),
        ),
        colors = colorsList
    )

    // Using the other coverage's entry sets will be error prone
    val coverageOverall = PieChartHelper.dataFromEntriesAndColors(
        entries = listOf(
            PieEntry(
                round( (analytics.averageHighCoverageBase + analytics.averageHighCoverageClear + analytics.averageHighCoveragePrimer)
                        /3f).toFloat(), stringResource(id = R.string.field_coverage_high)
            ),
            PieEntry(
                round((analytics.averageGoodCoverageBase + analytics.averageGoodCoverageClear + analytics.averageGoodCoveragePrimer)
                        /3f).toFloat(), stringResource(id = R.string.field_coverage_good)
            ),
            PieEntry(
                round((analytics.averageLowCoverageBase + analytics.averageLowCoverageClear + analytics.averageLowCoveragePrimer)
                        /3f).toFloat(), stringResource(id = R.string.field_coverage_low)
            )
        ),
        colors = colorsList
    )

    // val coverageOverall = PieChartHelper.dataFromGrades(analytics.overallGrade.groupingBy { it }.eachCount().toSortedMap())

    val coveragePieDataSet: Flow<PieDataSet> = flow {
        when(selectedCoverageType) {
            CoverageType.OVERALL -> emit(coverageOverall)
            CoverageType.PRIMER -> emit(coveragePrimer)
            CoverageType.BASE -> emit(coverageBase)
            CoverageType.CLEAR -> emit(coverageClear)
        }
    }

    return coveragePieDataSet
}