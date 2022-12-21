package com.tradiebot.cythero.presentation.analytics.components.reports.part.cards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.mikephil.charting.data.PieDataSet
import com.tradiebot.cythero.R
import com.tradiebot.cythero.app.ui.analytics.AnalyticsScreenState
import com.tradiebot.cythero.domain.analytics.CoverageType
import com.tradiebot.cythero.domain.analytics.Grade
import com.tradiebot.cythero.domain.analytics.part.model.AnalyticsPart
import com.tradiebot.cythero.presentation.analytics.components.AnalyticsPairField
import com.tradiebot.cythero.presentation.components.CytheroCard
import com.tradiebot.cythero.presentation.components.chart.PieChart
import com.tradiebot.cythero.presentation.components.chart.PieChartHelper
import kotlinx.coroutines.flow.flow
import kotlin.math.round

@Composable
fun AnalyticsPartCoverageBreakdownCard(
    state: AnalyticsScreenState.PartSuccess,
    selectedCoverageType: CoverageType,
) {
    val analytics = state.analytics[0]

    val gradePieDataSet = generateDataSet(
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
            PieChart(dataSet = flow { emit(gradePieDataSet) })
        }

        AnalyticsPairField(
            key = stringResource(R.string.field_coverage_high),
            value = (gradePieDataSet.entries.firstOrNull { e -> e.label.equals(stringResource(R.string.field_coverage_high))}?.value ?: 0).toInt().toString()
        )
        
        AnalyticsPairField(
            key = stringResource(R.string.field_coverage_good),
            value = (gradePieDataSet.entries.firstOrNull { e -> e.label.equals(stringResource(R.string.field_coverage_good))}?.value ?: 0).toInt().toString()
        )

        AnalyticsPairField(
            key = stringResource(R.string.field_coverage_low),
            value = (gradePieDataSet.entries.firstOrNull { e -> e.label.equals(stringResource(R.string.field_coverage_low))}?.value ?: 0).toInt().toString()
        )
        
        AnalyticsPairField(
            key = stringResource(R.string.field_coverage_no_coverage),
            value = (gradePieDataSet.entries.firstOrNull { e -> e.label.equals(stringResource(R.string.field_coverage_no_coverage))}?.value ?: 0).toInt().toString()
        )
    }
}

@Composable
private fun generateDataSet(
    analytics: AnalyticsPart,
    selectedCoverageType: CoverageType,
): PieDataSet {
    val colorsList = listOf(
            Grade.C.rgb,
            Grade.A.rgb,
            Grade.B.rgb,
            Grade.NAN.rgb
    )

    val coveragePrimer = PieChartHelper.generateDataSetPositive(
            data = listOf(
                Pair(round(analytics.averageHighCoveragePrimer).toFloat(), stringResource(R.string.field_coverage_high)),
                Pair(round(analytics.averageGoodCoveragePrimer).toFloat(), stringResource(R.string.field_coverage_good)),
                Pair(round(analytics.averageLowCoveragePrimer).toFloat(), stringResource(R.string.field_coverage_low)),
                Pair(
                    first = (100f - (round(analytics.averageHighCoveragePrimer) +
                        round(analytics.averageGoodCoveragePrimer) +
                        round(analytics.averageLowCoveragePrimer))).toFloat(),
                    second = stringResource(R.string.field_coverage_no_coverage)
                )
                
            ),
            colors = colorsList
    )
    
    val coverageBase = PieChartHelper.generateDataSetPositive(
        data = listOf(
            Pair(round(analytics.averageHighCoverageBase).toFloat(), stringResource(R.string.field_coverage_high)),
            Pair(round(analytics.averageGoodCoverageBase).toFloat(), stringResource(R.string.field_coverage_good)),
            Pair(round(analytics.averageLowCoverageBase).toFloat(), stringResource(R.string.field_coverage_low)),
            Pair(
                first = (100f - (round(analytics.averageHighCoverageBase) +
                    round(analytics.averageGoodCoverageBase) +
                    round(analytics.averageLowCoverageBase))).toFloat(),
                second = stringResource(R.string.field_coverage_no_coverage)
            )
        
        ),
        colors = colorsList
    )
    
    val coverageClear = PieChartHelper.generateDataSetPositive(
        data = listOf(
            Pair(round(analytics.averageHighCoverageClear).toFloat(), stringResource(R.string.field_coverage_high)),
            Pair(round(analytics.averageGoodCoverageClear).toFloat(), stringResource(R.string.field_coverage_good)),
            Pair(round(analytics.averageLowCoverageClear).toFloat(), stringResource(R.string.field_coverage_low)),
            Pair(
                first = (100f - (round(analytics.averageHighCoverageClear) +
                    round(analytics.averageGoodCoverageClear) +
                    round(analytics.averageLowCoverageClear))).toFloat(),
                second = stringResource(R.string.field_coverage_no_coverage)
            )
        
        ),
        colors = colorsList
    )
    
    val coverageOverallHigh = round(
        (analytics.averageHighCoverageBase +
            analytics.averageHighCoverageClear +
            analytics.averageHighCoveragePrimer) / 3f).toFloat()

    val coverageOverallGood = round(
        (analytics.averageGoodCoverageBase +
            analytics.averageGoodCoverageClear +
            analytics.averageGoodCoveragePrimer) / 3f).toFloat()

    val coverageOverallLow = round(
        (analytics.averageLowCoverageBase +
            analytics.averageLowCoverageClear +
            analytics.averageLowCoveragePrimer) / 3f).toFloat()
    
    val coverageOverall = PieChartHelper.generateDataSetPositive(
        data = listOf(
            Pair(coverageOverallHigh, stringResource(R.string.field_coverage_high)),
            Pair(coverageOverallGood, stringResource(R.string.field_coverage_good)),
            Pair(coverageOverallLow, stringResource(R.string.field_coverage_low)),
            Pair(
                first = 100f - (coverageOverallHigh + coverageOverallGood + coverageOverallLow),
                second = stringResource(R.string.field_coverage_no_coverage)
            )
        ),
        colors = colorsList
    )

    return when (selectedCoverageType) {
        CoverageType.OVERALL -> coverageOverall
        CoverageType.PRIMER -> coveragePrimer
        CoverageType.BASE -> coverageBase
        CoverageType.CLEAR -> coverageClear
    }
}
